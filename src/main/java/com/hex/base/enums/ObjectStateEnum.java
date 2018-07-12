package com.hex.base.enums;

import lombok.Getter;

@Getter
public enum ObjectStateEnum implements CodeEnum {

    BLOCK_UP(-1, "停用"),
    START_USING(1, "启用"),;

    private Integer code;

    private String msg;

    ObjectStateEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
