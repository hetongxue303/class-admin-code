package com.hetongxue.system.controller;

import com.hetongxue.base.constant.Constant;
import com.hetongxue.base.response.Result;
import com.hetongxue.configuration.redis.RedisUtils;
import com.hetongxue.configuration.security.exception.JwtAuthenticationException;
import com.hetongxue.configuration.security.utils.SecurityUtils;
import com.hetongxue.system.domain.Account;
import com.hetongxue.system.domain.Menu;
import com.hetongxue.system.domain.Role;
import com.hetongxue.system.domain.User;
import com.hetongxue.system.domain.vo.MenuVo;
import com.hetongxue.system.domain.vo.RouterVo;
import com.hetongxue.system.domain.vo.TokenVo;
import com.hetongxue.system.domain.vo.UserVo;
import com.hetongxue.system.service.MenuService;
import com.hetongxue.system.service.RoleService;
import com.hetongxue.system.service.UserService;
import com.hetongxue.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户控制器
 *
 * @author 何同学
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private MenuService menuService;
    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private RedisUtils redisUtils;

    /**
     * 获取用户信息
     *
     * @return com.hetongxue.base.response.Result
     */
    @GetMapping("/getUserInfo")
    public Result getUserInfo(HttpServletResponse response) {
        // 获取账户信息
        Long accountId = SecurityUtils.getAccount().getAccountId();
        // 获取用户信息
        User user = userService.selectOneByAccountID(accountId);
        if (!ObjectUtils.isEmpty(user)) {

            // 获取角色列表
            List<Role> roleList = roleService.selectRoleByAccountId(accountId);

            // 获取菜单列表
            List<Menu> menuList = menuService.selectMenuListByAccountID(accountId);
            List<MenuVo> menus = SecurityUtils.generateMenu(menuList, 0L);

            // 获取路由列表
            List<RouterVo> routers = SecurityUtils.generateRouter(menuList, 0L);

            // 生成权限列表
            String authority = SecurityUtils.generateAuthorityByKey(menuList);

            response.setStatus(HttpStatus.OK.value());
            return Result.Success(new UserVo(roleList.get(0).getRoleKey(), authority, routers, menus)).setMessage("获取用户信息成功");
        }
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return Result.Error().setMessage("获取用户信息失败");
    }

    @PostMapping("/refreshToken")
    public Result refreshToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 拿到token
            String token = request.getHeader(Constant.SECURITY_AUTHORIZATION);
            if (ObjectUtils.isEmpty(token)) {
                throw new JwtAuthenticationException("token异常");
            }
            // 校验token合法性
            Claims claims = jwtUtils.parseToken(token);
            if (ObjectUtils.isEmpty(claims)) {
                throw new JwtAuthenticationException("token异常");
            }
            Account account = ((Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            String newToken = jwtUtils.generateToken(account.getAccountId(), account.getUsername());
            // 删除之前redis中的token
            redisUtils.delete(Constant.SECURITY_AUTHORIZATION);
            redisUtils.setValue(Constant.SECURITY_AUTHORIZATION, newToken, Constant.REDIS_TIMEOUT, Constant.REDIS_TIMEUNIT);
            response.setHeader(Constant.SECURITY_AUTHORIZATION, newToken);
            return Result.Success(new TokenVo(Constant.TOKEN_EXPIRATION_TIME, newToken)).setMessage("刷新token成功！");
        } catch (JwtAuthenticationException e) {
            return Result.Error().setMessage("刷新token失败,请重新登录！");
        }
    }

}