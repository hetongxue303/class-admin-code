package com.hetongxue.handler;

import com.hetongxue.base.response.Result;
import com.hetongxue.enums.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理类
 *
 * @author 何同学
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

    /**
     * 空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    public Result nullPointerException(NullPointerException e) {
        e.printStackTrace();
        log.error(ResponseCode.NULL_POINTER.getMessage());
        return Result.Error().setMessage(ResponseCode.NULL_POINTER.getMessage()).setCode(ResponseCode.NULL_POINTER.getCode());
    }

}