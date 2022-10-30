package com.hetongxue.system.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 用户VO
 *
 * @author 何同学
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserVo implements Serializable {

    /**
     * 角色名称
     */
    private String role;
    /**
     * 权限列表
     */
    private String authentication;
    /**
     * 路由列表
     */
    private List<RouterVo> routers;
    /**
     * 菜单列表
     */
    private List<MenuVo> menus;

}