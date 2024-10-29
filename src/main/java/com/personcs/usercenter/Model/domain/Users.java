package com.personcs.usercenter.Model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Users implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 昵称
     */
    private String username;

    /**
     * 登录账号
     */
    private String userAccount;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 性别 (0: 未知, 1: 男, 2: 女)
     */
    private Integer gender;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户状态 (0: 正常, 1: 禁用等)
     */
    private Integer userStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除 (0: 未删除, 1: 已删除)
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 0-普通用户，1-管理员，2-超级管理员
     */
    private Integer userrole;
    /**
     * 星球编码
     */
    private String planetCode;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
