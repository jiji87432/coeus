package com.pay.coeus.api.inner.bean;

public enum ResponseCode {

    SUCCESS(20, "成功"),
    FAIL(30,"操作失败"),
    UNKONW_ERROR(40, "未知错误"),
    EXCEPTION_ERROR(98,"系统错误"),
    ;

    private Integer code;

    private String type;

    ResponseCode(Integer code, String type) {
        this.code = code;
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public String getType() {
        return type;
    }
	
}
