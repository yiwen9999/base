package com.hex.base.enums;

import lombok.Getter;

/**
 * User: hexuan
 * Date: 2018/7/9
 * Time: 上午10:22
 */
@Getter
public enum MeetingStateEnum implements CodeEnum {

    NOT_START(0, "未开展"),
    START(1, "开展中"),
    FINISH(2, "已结束"),;

    private Integer code;

    private String msg;

    MeetingStateEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
