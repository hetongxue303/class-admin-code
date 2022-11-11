package com.hetongxue.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hetongxue.system.domain.User;
import com.hetongxue.system.domain.dto.PageQuery;

import java.util.List;

/**
 * 学生业务
 *
 * @author 何同学
 */
public interface StudentService extends IService<User> {

    List<User> getStudentList();
    Page<User> getStudentListPage(PageQuery query);

    boolean addOneStudent(User user);

    boolean batchAddStudents(List<User> users);

    boolean deleteOneStudent(Long studentID);

    boolean batchDeleteStudent(List<Long> studentIds);

    boolean updateStudent(User user);

    boolean batchUpdateStudents(List<User> users);

    User getOneStudent(Long studentID);
}