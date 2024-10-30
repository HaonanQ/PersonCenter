package com.personcs.usercenter.Respons;

public enum ErrorCode {
    /**
     * 状态码枚举类
     * 7开头是成功，6开头是失败
     */

    SUCCESSLogin(7001, "登录成功", "登录成功"),
    SUCCESSREGISTER(7002, "注册成功", "注册成功"),
    SUCCESSLogout(7003, "退出成功", "退出成功"),
    SUCCESS_UPDATE_INFO(7004, "更新信息成功","更新或搜索信息成功"),
    SUCESS_DELETE_USER(7005, "删除用户成功","管理员删除用户"),

    LESSPRAM(6001,"参数缺少","密码，账户名等为空，或没有任何参数"),
    ACPASSWORD_ERROR(6002, "密码或账户错误", "密码错误，账户名不存在"),
    ERROR_UNKNOW(6003, "未知错误", "未知错误"),
    ERR_AC_LENGTH(6004, "账户名长度错误", "账户名长度不正确"),
    ACCOUNT_EXIST(6005, "账户取名不合法或账户已被注册", "账户取名不合法或账户已被注册"),
    TWO_PASSWORD_NOT_MATCH(6006, "两次密码不匹配", "两次输入的密码不一致"),
    SYSTEM_ERROR(6007, "系统内部异常", ""),
    NO_AUTH(6008, "无权限", ""),
    NOT_LOGIN(6009, "未登录", ""),
    PARAMS_ERROR(6010, "请求参数错误", ""),
    NULL_ERROR(6011, "请求数据为空", ""),
    FAIR_DELETE_USER(6012, "删除用户失败", "用户不存在或已删除"),
    ERR_PASS_LENGTH(6013, "密码不足8位", "密码长度不正确");

    private final int code;

    /**
     * 状态码信息
     */
    private final String message;

    /**
     * 状态码描述（详情）
     */
    private final String description;


    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
