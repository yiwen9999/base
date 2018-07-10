package com.hex.base.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 日程
 * <p>
 * User: hexuan
 * Date: 2018/7/5
 * Time: 下午4:57
 */
@Entity
@DynamicUpdate
@Getter
@Setter
public class Schedule implements Serializable {

    @Id
    private String id;

    /**
     * 地点
     */
    private String place;

    /**
     * 时间段
     */
    private String timeStr;

    /**
     * 日期
     */
    private Date time;

    /**
     * 会议内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public Schedule() {
    }
}
