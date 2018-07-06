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
 * 会议
 * <p>
 * User: hexuan
 * Date: 2018/7/5
 * Time: 下午4:43
 */
@Entity
@DynamicUpdate
@Getter
@Setter
public class Meeting implements Serializable {

    @Id
    @GeneratedValue
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
    private String time;

    /**
     * 会议小贴士
     */
    private String tips;

    /**
     * 卫星会信息
     */
    private String satelliteMeetingInfo;

    /**
     * 卫星会图片
     */
    private String satelliteMeetingImg;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public Meeting() {
    }
}
