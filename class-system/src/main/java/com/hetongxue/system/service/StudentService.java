package com.hetongxue.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hetongxue.system.domain.User;

import java.util.List;

/**
 * 学生业务
 *
 * @author 何同学
 */
public interface StudentService extends IService<User> {

    List<User> getStudentList();

}