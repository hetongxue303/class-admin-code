package com.hetongxue.utils;

import com.hetongxue.base.constant.Constant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

/**
 * JWT工具类
 *
 * @author 何同学
 */
@Component
public class JwtUtils {

    /**
     * 过期时间(单位：ms) 默认3天
     */
    private static final long EXPIRATION_TIME = Constant.TOKEN_EXPIRATION_TIME;
    /**
     * 密钥
     */
    private static final String SECRET = Constant.TOKEN_SECRET;

    /**
     * 签名算法
     */
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = Constant.TOKEN_SIGNATURE_ALGORITHM;
    /**
     * Bearer
     */
    private static final String PREFIX = Constant.TOKEN_PREFIX;

    /**
     * 生成JWT
     *
     * @param ID       唯一ID
     * @param username 用户名字
     * @return java.lang.String
     */
    public String generateToken(Long ID, String username) {
        String token = Jwts.builder()
                // 设置头部参数
                .setHeaderParam("typ", "JWT")
                // 设置ID
                .setId(String.valueOf(ID))
                // 设置主题
                .setSubject(username)
                // 设置发行时间
                .setIssuedAt(new Date())
                // 设置过期时间(claim设置在过期时间之前 否则可能会出现过期时间不生效问题)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                // 设置签发方式
                .signWith(SIGNATURE_ALGORITHM, SECRET).compact();
        return Constant.TOKEN_IS_PREFIX ? PREFIX + token : token;
    }

    /**
     * 解析JWT
     *
     * @param token tokan值
     * @return io.jsonwebtoken.Claims
     */
    public Claims parseToken(String token) {
        try {
            if (Constant.TOKEN_IS_PREFIX) {
                return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.substring(PREFIX.length())).getBody();
            }
            return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 是否存在token
     */
    public boolean isToken(String token) {
        if (Constant.TOKEN_IS_PREFIX) {
            return Objects.isNull(Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.substring(PREFIX.length(), token.length())).getBody());
        }
        return Objects.isNull(Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody());
    }

    /**
     * 判断jwt是否过期
     */
    public boolean isExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

}