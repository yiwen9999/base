package com.hex.base.vo;

import lombok.Data;

import java.util.Date;

/**
 * User: hexuan
 * Date: 2018/11/6
 * Time: 3:37 PM
 */
@Data
public class UserInfoVO {

    private String id;

    private String openId;

    private String nickName;

    private Boolean male;

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
     * 地点id
     */
    private String placeId;

    /**
     * 地点名称
     */
    private String placeName;

    /**
     * 签到次数
     */
    private Integer attendanceTimes;

    private Date createTime;
}
