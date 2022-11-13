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
     * 过期时间(单位:毫秒)
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
    public Claims getClaims(String token) {
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
    public boolean isNull(String token) {
        if (Constant.TOKEN_IS_PREFIX) {
            return Objects.isNull(Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.substring(PREFIX.length())).getBody());
        }
        return Objects.isNull(Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody());
    }

    /**
     * 刷新token
     *
     * @param token
     * @return java.lang.String
     */
    public String refreshToken(String token) {
        try {
            return generateToken(getTokenId(token), getTokenUsername(token));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取用户名
     *
     * @param token token
     * @return java.lang.String
     */
    public String getTokenUsername(String token) {
        try {
            return getClaims(token).getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取ID
     *
     * @param token
     * @return java.lang.Long
     */
    public Long getTokenId(String token) {
        try {
            return Long.valueOf(getClaims(token).getId());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 判断是否过期
     *
     * @param token
     * @return boolean
     */
    public boolean isExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

}