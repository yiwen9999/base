package com.hex.base.vo;

import lombok.Data;

import java.util.Date;

/**
 * User: hexuan
 * Date: 2018/10/18
 * Time: 10:52 AM
 */
@Data
public class AttendanceVO {

    private String id;

    /**
     * 会议名称
     */
    private String meetingName;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 地点名称
     */
    private String placeName;

    /**
     * 微信昵称
     */
    private String nickName;

    /**
     * 姓名
     */
    private String name;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 所在医院
     */
    private String hospital;

    /**
     * 是否中奖
     */
    private Boolean winningRecord;

    private Date createTime;

}
