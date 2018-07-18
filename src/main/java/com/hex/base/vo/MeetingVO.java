package com.hex.base.vo;

import lombok.Data;

/**
 * User: hexuan
 * Date: 2018/7/13
 * Time: 下午2:36
 */
@Data
public class MeetingVO {
    private Integer id;

    /**
     * 会议名称
     */
    private String name;

    /**
     * 地址信息
     */
    private String address;

    /**
     * 日期信息
     */
    private String timeStr;

    /**
     * 会议小贴士
     */
    private String tips;

    /**
     * 会议图片
     */
    private String img;

    /**
     * 会议logo
     */
    private String logo;

    /**
     * 状态值
     */
    private Integer state;

    /**
     * 状态
     */
    private String stateStr;
}
