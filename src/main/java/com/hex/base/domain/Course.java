package com.hex.base.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 课程
 *
 * User: hexuan
 * Date: 2018/7/5
 * Time: 下午5:19
 */
public class Course implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 视频名称
     */
    private String name;

    /**
     * 简介
     */
    private String intro;

    /**
     * 缩略图
     */
    private String icon;

    /**
     * 视频地址
     */
    private String videoPath;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public Course() {
    }
}
