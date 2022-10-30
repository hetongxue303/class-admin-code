package com.hetongxue.system.controller;

import com.hetongxue.base.response.Result;
import com.hetongxue.configuration.security.utils.SecurityUtils;
import com.hetongxue.system.domain.Role;
import com.hetongxue.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色控制器
 *
 * @author 何同学
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/getRoles")
    public Result getRoles() {
        List<Role> roles = roleService.selectRoleByUserId(SecurityUtils.getUser().getUserId());
        if (!ObjectUtils.isEmpty(roles)) {
            return Result.Success(roles).setMessage("获取角色信息成功");
        }
        return Result.Error().setMessage("获取角色信息失败");
    }

}