package com.hetongxue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.system.domain.User;
import com.hetongxue.system.domain.dto.PageQuery;
import com.hetongxue.system.mapper.UserMapper;
import com.hetongxue.system.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
    private final static Long ROLE_ID = 3L;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> getStudentList() {
        return userMapper.selectList(new LambdaQueryWrapper<User>().eq(User::getRoleId, ROLE_ID));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Page<User> getStudentListPage(PageQuery query) {
        return userMapper.selectPage(new Page<>(query.getCurrentPage(), query.getPageSize()), new LambdaQueryWrapper<User>().eq(User::getRoleId, ROLE_ID));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addOneStudent(User user) {
        return userMapper.insert(user) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchAddStudents(List<User> users) {
        return this.saveBatch(users);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteOneStudent(Long studentID) {
        return userMapper.delete(new LambdaQueryWrapper<User>().eq(User::getUserId, studentID)) > 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchDeleteStudent(List<Long> studentIds) {
        return userMapper.deleteBatchIds(studentIds) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateStudent(User user) {
        return userMapper.updateById(user) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchUpdateStudents(List<User> users) {
        return this.updateBatchById(users);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User getOneStudent(Long studentID) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserId, studentID).eq(User::getRoleId, ROLE_ID));
    }

}