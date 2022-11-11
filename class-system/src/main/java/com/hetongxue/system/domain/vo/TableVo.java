package com.hetongxue.system.domain.vo;

import com.hetongxue.system.domain.College;
import com.hetongxue.system.domain.Major;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 表格VO
 *
 * @author 何同学
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TableVo implements Serializable {

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 学院信息
     */
    private College college;
    /**
     * 专业信息
     */
    private Major major;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 学号
     */
    private Long userNo;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户电话
     */
    private String phone;
    /**
     * 用户性别(0:男 1:女 2:保密)
     */
    private String gender;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 帐号状态(0:正常 1:停用)
     */
    private boolean status;
    /**
     * 删除标志(0:存在 1:已删除)
     */
    private boolean delFlag;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}