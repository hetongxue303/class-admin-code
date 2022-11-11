package com.hetongxue.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hetongxue.base.response.Result;
import com.hetongxue.system.domain.User;
import com.hetongxue.system.domain.dto.PageQuery;
import com.hetongxue.system.service.StudentService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 学生控制器
 *
 * @author 何同学
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @GetMapping("/{studentID}")
    public Result getOneStudent(@PathVariable("studentID") Long studentID) {
        User student = studentService.getOneStudent(studentID);
        return !ObjectUtils.isEmpty(student) ? Result.Success(student).setMessage("获取学生列表成功") : Result.Error().setMessage("获取学生列表失败");
    }

    @GetMapping("/list")
    public Result getStudentList() {
        List<User> studentList = studentService.getStudentList();
        return !ObjectUtils.isEmpty(studentList) ? Result.Success(studentList).setMessage("获取学生列表成功") : Result.Error().setMessage("获取学生列表失败");
    }

    @GetMapping("/page/list")
    public Result getStudentListPage(@RequestBody PageQuery query) {
        System.out.println("query = " + query);
        Page<User> studentListPage = studentService.getStudentListPage(query);
        return !ObjectUtils.isEmpty(studentListPage) ? Result.Success(studentListPage).setMessage("获取学生列表成功") : Result.Error().setMessage("获取学生列表失败");
    }

    @PostMapping("/add")
    public Result addOneStudent(@RequestBody User user) {
        System.out.println("学生信息:" + user);
        return studentService.addOneStudent(user) ? Result.Success().setMessage("添加成功") : Result.Error().setMessage("添加失败");
    }

    @PostMapping("/batch/add")
    public Result batchAddStudents(List<User> users) {
        System.out.println("学生信息:" + users);
        return studentService.batchAddStudents(users) ? Result.Success().setMessage("添加成功") : Result.Error().setMessage("添加失败");
    }

    @DeleteMapping("/del/{studentID}")
    public Result deleteOneStudent(@PathVariable("studentID") Long studentID) {
        return studentService.deleteOneStudent(studentID) ? Result.Success().setMessage("删除成功") : Result.Error().setMessage("删除失败");
    }

    @DeleteMapping("/batch/del")
    public Result batchDeleteStudent(List<Long> studentIds) {
        return studentService.batchDeleteStudent(studentIds) ? Result.Success().setMessage("删除成功") : Result.Error().setMessage("删除失败");
    }

    @PutMapping("/update")
    public Result updateStudent(User user) {
        return studentService.updateStudent(user) ? Result.Success().setMessage("更新成功") : Result.Error().setMessage("更新失败");
    }

    @PutMapping("/batch/update")
    public Result batchUpdateStudents(List<User> users) {
        return studentService.batchUpdateStudents(users) ? Result.Success().setMessage("更新成功") : Result.Error().setMessage("更新失败");
    }

}