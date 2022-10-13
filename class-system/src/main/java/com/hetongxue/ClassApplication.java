package com.hetongxue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 程序入口
 *
 * @author 何同学
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.hetongxue.system.mapper")
public class ClassApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassApplication.class, args);
        System.out.println("项目启动成功...");
    }

}