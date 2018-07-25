package com.hex.base.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * User: hexuan
 * Date: 2018/7/24
 * Time: 下午4:52
 */
@Data
public class ScheduleForm {

    private String id;

    /**
     * 会议id
     */
    @NotNull(message = "会议id不能为空")
    private Integer meetingId;

    /**
     * 讲者id
     */
    @NotNull(message = "讲者id不能为空")
    private Integer speakerId;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date time;
}
