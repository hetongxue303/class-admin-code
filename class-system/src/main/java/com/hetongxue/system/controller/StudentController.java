package com.hetongxue.system.controller;

import com.hetongxue.base.response.Result;
import com.hetongxue.system.domain.User;
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
        return null;
    }

    @GetMapping("/list")
    public Result getStudentList() {
        List<User> studentList = studentService.getStudentList();
        if (!ObjectUtils.isEmpty(studentList)) {
            return Result.Success(studentList).setMessage("获取学生列表成功");
        }
        return Result.Error().setMessage("获取学生列表失败");
    }

    @PostMapping("/add")
    public Result addOneStudent(User user) {
        return null;
    }

    @PostMapping("/batch/add")
    public Result batchAddStudents(List<User> users) {
        return null;
    }

    @DeleteMapping("/del/{studentID}")
    public Result deleteOneStudent(@PathVariable("studentID") Long studentID) {
        return null;
    }

    @DeleteMapping("/batch/del")
    public Result batchDeleteStudent(List<Long> studentIds) {
        return null;
    }

    @PutMapping("/update")
    public Result updateStudent(User user) {
        return null;
    }

    @PutMapping("/batch/update")
    public Result batchUpdateStudents(List<User> user) {
        return null;
    }

}