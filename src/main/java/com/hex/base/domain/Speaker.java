package com.hex.base.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 讲者
 * <p>
 * User: hexuan
 * Date: 2018/7/5
 * Time: 下午5:03
 */
@Entity
@DynamicUpdate
@Data
public class Speaker implements Serializable {

    @Id
    @GeneratedValue
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
    private String photo = "default_speaker_photo.jpg";

    /**
     * 简介
     */
    private String intro;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
