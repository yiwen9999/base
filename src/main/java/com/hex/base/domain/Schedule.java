package com.hex.base.domain;

import lombok.Data;
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
@Data
public class Schedule implements Serializable {

    @Id
    private String id;

    /**
     * 会议id
     */
    private Integer meetingId;

    /**
     * 讲者id
     */
    private Integer speakerId;

    /**
     * 讲者名称
     */
    private String speakerName;

    /**
     * 地点
     */
    private String place;

    /**
     * 会议内容
     */
    private String content;

    /**
     * 时间段
     */
    private String timeStr;

    /**
     * 日期
     */
    private Date time;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}
