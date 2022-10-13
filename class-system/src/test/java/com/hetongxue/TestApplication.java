package com.hetongxue;

import com.hetongxue.system.domain.User;
import com.hetongxue.system.service.UserService;
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
    private UserService userService;

    @Test
    void pass() {
        User admin = userService.selectOneByUsername("admin");
        System.out.println("admin = " + admin);
    }

}