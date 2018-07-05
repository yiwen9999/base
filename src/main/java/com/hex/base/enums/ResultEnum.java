package com.hex.base.enums;

public enum ResultEnum {
    UN_KNOW_ERRO(-1, "未知错误"),
    SUCCESS(0, "成功"),
    UN_LOGIN(101, "未登录"),
    NO_ROLE(102, "操作人员没有相对应的角色"),
    ERROR_PARAM(103, "传递的参数错误"),
    ERROR_DELETE(104, "未能删除"),
    UPLOAD_FILE_IS_NULL(105, "上传文件为空"),
    UPLOAD_FAIL(106, "上传失败"),
    ERROR_SAVE(107, "未能保存"),
    ERROR_PASSWORD(108, "密码错误"),
    ERROR_USERNAME(109, "登录名不存在"),
    ERROR_IDENTITY(110, "登录账号身份错误"),
    ERROR_NULLPARAM(111, "不能为空"),
    ERROR_EXIST(112, "已被使用，请重新输入");

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
