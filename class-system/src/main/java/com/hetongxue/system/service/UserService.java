package com.hetongxue.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hetongxue.system.domain.User;

import java.util.List;

/**
 * 用户业务
 *
 * @author 何同学
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户名查询用户信息
     */
    User selectOneByUsername(String username);

    /**
     * 根据用户ID查询用户信息
     */
    User selectOneByUserID(Long userId);

    /**
     * 查询所有学生信息
     *
     * @return java.util.List<com.hetongxue.system.domain.User>
     */
    List<User> selectUserList(String key);

}