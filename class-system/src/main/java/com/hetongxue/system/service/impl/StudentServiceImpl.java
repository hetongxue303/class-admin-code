package com.hetongxue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.system.domain.User;
import com.hetongxue.system.mapper.UserMapper;
import com.hetongxue.system.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 学生业务实现
 *
 * @author 何同学
 */
@Service
@Transactional
public class StudentServiceImpl extends ServiceImpl<UserMapper, User> implements StudentService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> getStudentList() {
        return userMapper.selectList(new LambdaQueryWrapper<User>().eq(User::getRoleId, 3));
    }

}