package com.hex.base.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * User: hexuan
 * Date: 2018/7/27
 * Time: 上午10:38
 */
@Data
public class ScheduleCondition {

    /**
     * 会议id
     */
    private Integer meetingId;

    /**
     * 日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date time;
}
