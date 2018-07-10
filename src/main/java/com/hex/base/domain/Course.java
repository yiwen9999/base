package com.hex.base.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 课程
 * <p>
 * User: hexuan
 * Date: 2018/7/5
 * Time: 下午5:19
 */
@Entity
@DynamicUpdate
@Getter
@Setter
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
