package com.hetongxue.system.service.impl;

import com.hetongxue.configuration.security.entity.LoginInfo;
import com.hetongxue.system.domain.Account;
import com.hetongxue.system.service.AccountService;
import com.hetongxue.system.service.MenuService;
import com.hetongxue.system.service.RoleService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * UserDetailsService实现类
 *
 * @author 何同学
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private AccountService accountService;
    @Resource
    private RoleService roleService;
    @Resource
    private MenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取账户信息
        Account account = accountService.selectOneByUsername(username);
        if (Objects.isNull(account)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }


        // 查询角色列表
//        List<Role> roleList = roleService.selectRoleByUserId(user.getUserId());
        // 查询菜单列表
//        List<Menu> menuList = menuService.selectMenuByUserId(user.getUserId());
        // 生成权限列表
//        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(SecurityUtils.generateAuthority(roleList, menuList));
        // 生成菜单列表
//        List<MenuVo> menuVos = SecurityUtils.generateMenu(menuList, 0L);
        // 生成路由列表
//        List<RouterVo> routers = SecurityUtils.generateRouter(menuList, 0L);

        return new LoginInfo(account, null);
    }

}