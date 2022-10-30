package com.hetongxue.system.controller;

import com.hetongxue.base.response.Result;
import com.hetongxue.configuration.security.utils.SecurityUtils;
import com.hetongxue.system.domain.Menu;
import com.hetongxue.system.domain.Role;
import com.hetongxue.system.domain.User;
import com.hetongxue.system.domain.vo.MenuVo;
import com.hetongxue.system.domain.vo.RouterVo;
import com.hetongxue.system.domain.vo.UserVo;
import com.hetongxue.system.service.MenuService;
import com.hetongxue.system.service.RoleService;
import com.hetongxue.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private HttpServletResponse response;

    /**
     * 获取用户信息
     *
     * @return com.hetongxue.base.response.Result
     */
    @GetMapping("/getUserInfo")
    public Result getUserInfo() {
        // 获取用户信息
        User user = userService.selectOneByUsername(SecurityUtils.getUser().getUsername());
        if (!ObjectUtils.isEmpty(user)) {

            // 获取角色列表
            List<Role> roleList = roleService.selectRoleByUserId(user.getUserId());
            // 获取菜单列表
            List<Menu> menuList = menuService.selectMenuByUserId(user.getUserId());
            List<MenuVo> menus = SecurityUtils.generateMenu(menuList, 0L);
            // 获取路由列表
            List<RouterVo> routers = SecurityUtils.generateRouter(menuList, 0L);
            // 获取权限列表
            String authority = SecurityUtils.generateAuthorityByKey(menuList);

            response.setStatus(HttpStatus.OK.value());

            return Result.Success(new UserVo(roleList.get(0).getRoleKey(), authority, routers, menus)).setMessage("获取用户信息成功");
        }
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return Result.Error().setMessage("获取用户信息失败");
    }

    @GetMapping("/getUserList/{key}")
    public Result getUserList(@PathVariable String key) {
        List<User> userList = userService.selectUserList(key);
        if (!ObjectUtils.isEmpty(userList)) {
            return Result.Success(userList).setMessage("获取数据成功");
        }
        return Result.Error().setMessage("获取数据失败");
    }

}