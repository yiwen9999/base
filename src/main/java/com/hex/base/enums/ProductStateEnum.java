package com.hex.base.enums;

import lombok.Getter;

@Getter
public enum ProductStateEnum implements CodeEnum {

    DOWN(-1, "下架"),
    UP(1, "上架"),;

    private Integer code;

    private String msg;

    ProductStateEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
