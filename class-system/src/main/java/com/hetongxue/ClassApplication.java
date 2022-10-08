package com.hetongxue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 程序入口
 *
 * @author 何同学
 */
@SpringBootApplication
public class ClassApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassApplication.class, args);
        System.out.println("项目启动成功...");
    }

}