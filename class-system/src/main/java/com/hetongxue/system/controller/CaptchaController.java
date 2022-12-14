package com.hetongxue.system.controller;

import com.hetongxue.base.constant.Constant;
import com.hetongxue.base.response.Result;
import com.hetongxue.configuration.redis.RedisUtils;
import com.wf.captcha.ArithmeticCaptcha;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 验证码控制器
 *
 * @author 何同学
 */
@RestController
@RequestMapping("/auth")
public class CaptchaController {

    /**
     * 存储时间
     */
    private static final long TIME = 60;
    /**
     * 存储单位
     */
    private static final TimeUnit TIMEUNIT = TimeUnit.SECONDS;
    /**
     * 图片宽度
     */
    private static final int WIDTH = 111;
    /**
     * 图片高度
     */
    private static final int HEIGHT = 36;
    /**
     * 图片长度
     */
    private static final int LENGTH = 2;

    @Resource
    private RedisUtils redisUtils;

    @GetMapping("/captchaImage")
    public Result getVerify() {
        // 在java11中使用Nashorn engine  会出现 Warning: Nashorn engine is planned to be removed from a future JDK release
        System.setProperty("nashorn.args", "--no-deprecation-warning");
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(WIDTH, HEIGHT, LENGTH);
        // 设置60秒过期
        redisUtils.setValue(Constant.SECURITY_CAPTCHA, captcha.text(), TIME, TIMEUNIT);
        return Result.Success(captcha.toBase64());
    }

}