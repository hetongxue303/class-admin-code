package com.hetongxue;

import com.hetongxue.system.mapper.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author 何同学
 * <p>
 * 测试类
 */
@SpringBootTest
public class TestApplication {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private CollegeMapper collegeMapper;
    @Resource
    private MajorMapper majorMapper;
    @Resource
    private AccountMapper accountMapper;

    @Test
    void test() {
        System.out.println("userMapper.selectList(null) = " + userMapper.selectList(null));
        System.out.println("roleMapper.selectList(null) = " + roleMapper.selectList(null));
        System.out.println("menuMapper.selectList(null) = " + menuMapper.selectList(null));
        System.out.println("collegeMapper.selectList(null) = " + collegeMapper.selectList(null));
        System.out.println("majorMapper.selectList(null) = " + majorMapper.selectList(null));
        System.out.println("accountMapper.selectList(null) = " + accountMapper.selectList(null));
    }

}