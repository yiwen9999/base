package com.hex.base.vo;

import lombok.Data;

import java.util.Date;

/**
 * User: hexuan
 * Date: 2018/7/27
 * Time: 上午11:12
 */
@Data
public class ScheduleVO {

    private String id;

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
}
