package com.hetongxue.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hetongxue.system.domain.User;
import com.hetongxue.system.domain.vo.UserVo;

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

}