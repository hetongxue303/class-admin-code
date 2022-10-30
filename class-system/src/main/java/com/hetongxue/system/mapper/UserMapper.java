package com.hetongxue.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hetongxue.system.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户Mapper
 *
 * @author 何同学
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select user_id from sys_user_role where role_id in(SELECT role_id FROM sys_role where role_key = #{key})")
    List<Long> getUsersId(@Param("key") String role_key);
}