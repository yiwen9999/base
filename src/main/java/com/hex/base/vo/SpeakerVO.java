package com.hex.base.vo;

import lombok.Data;

/**
 * User: hexuan
 * Date: 2018/7/12
 * Time: 下午4:41
 */
@Data
public class SpeakerVO {
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 职称
     */
    private String professionalTitle;

    /**
     * 任职医院信息
     */
    private String hospitalInfo;

    /**
     * 照片
     */
    private String photo;

    /**
     * 简介
     */
    private String intro;
}
