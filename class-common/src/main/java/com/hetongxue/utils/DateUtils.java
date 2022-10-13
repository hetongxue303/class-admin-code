package com.hetongxue.utils;

import com.hetongxue.enums.TimeUnit;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 日期时间工具类
 *
 * @author 何同学
 */
@Component
public class DateUtils {

    /**
     * 常量定义
     */
    long NANOSECONDS = 1 / 1000 / 1000L;
    long MICROSECONDS = 1 / 1000L;
    long MILLISECONDS = 1L;
    long SECONDS = 1000L;
    long MINUTES = 60 * 1000L;
    long HOURS = 60 * 60 * 1000L;
    long DAYS = 24 * 60 * 60 * 1000L;
    long WEEKS = 7 * 24 * 60 * 60 * 1000;
    long MONTHS = 30 * 24 * 60 * 60 * 1000L;
    long YEARS = 365 * 24 * 60 * 60 * 1000L;

    /**
     * 过期时间计算
     *
     * @param timeout 超时时间
     * @param unit    单位
     * @return Date - 过期时间
     */
    public Date expires(int timeout, TimeUnit unit) {
        long time = 0L;
        long now = new Date().getTime();
        switch (unit) {
            case NANOSECONDS:
                time = now + timeout * NANOSECONDS;
                break;
            case MICROSECONDS:
                time = now + timeout * MICROSECONDS;
                break;
            case MILLISECONDS:
                time = now + timeout * MILLISECONDS;
                break;
            case SECONDS:
                time = now + timeout * SECONDS;
                break;
            case MINUTES:
                time = now + timeout * MINUTES;
                break;
            case HOURS:
                time = now + timeout * HOURS;
                break;
            case DAYS:
                time = now + timeout * DAYS;
                break;
            case WEEKS:
                time = now + timeout * WEEKS;
                break;
            case MONTHS:
                time = now + timeout * MONTHS;
                break;
            case YEARS:
                time = now + timeout * YEARS;
                break;
        }
        return new Date(time);
    }

}