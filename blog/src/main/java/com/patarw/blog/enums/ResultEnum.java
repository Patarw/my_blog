package com.patarw.blog.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * 统一错误码响应
 */
@ToString
public enum ResultEnum {
    SYSTEM_ERROR(-1, "系统错误,请联系管理员"),
    SUCCESS(1, "成功"),
    PARAM_ERROR(2, "账号或密码错误，登陆失败"),
    SQL_UNIQUE_ERROR(3, "数据库已有重复值"),
    REMOTE_ERROR(4, "远程调用失败"),
    AUTHENTICATION_ERROR(5, "您还未登录"),
    USER_NOT_EXIST(6, "用户不存在"),
    NO_GOODS_MSG(7,"商品不存在"),
    ROLE_ERROR(8, "您不具备该角色权限"),
    ;

    @Getter
    private Integer code;
    @Getter
    private String errMsg;

    ResultEnum(Integer code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }
}
