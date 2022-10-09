package com.hetongxue.configuration.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.hetongxue.base.constant.Common;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;

import java.util.Date;

/**
 * my batis plus配置类
 *
 * @author 何同学
 */
public class MyBatisPlusConfiguration implements MetaObjectHandler {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // 防全表更新插件
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return interceptor;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName(Common.CREATE_TIME_KEY, new Date(), metaObject);
        this.setFieldValByName(Common.UPDATE_TIME_KEY, new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName(Common.UPDATE_TIME_KEY, new Date(), metaObject);
    }

}