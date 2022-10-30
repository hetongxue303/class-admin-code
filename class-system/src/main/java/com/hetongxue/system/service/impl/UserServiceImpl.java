package com.hetongxue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.system.domain.User;
import com.hetongxue.system.mapper.UserMapper;
import com.hetongxue.system.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户业务实现
 *
 * @author 何同学
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User selectOneByUsername(String username) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User selectOneByUserID(Long userId) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserId, userId));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> selectUserList(String key) {
        List<Long> students = userMapper.getUsersId(key);
        return userMapper.selectList((new LambdaQueryWrapper<User>().in(User::getUserId, students)));
    }

}