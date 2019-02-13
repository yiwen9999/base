package com.hex.base.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * User: hexuan
 * Date: 2018/8/10
 * Time: 下午5:01
 */
@Data
public class ScheduleForMeetingVO {

    /**
     * 日期字符串
     */
    String timeStr;

    List<ScheduleVO> scheduleVOList = new ArrayList<>();
}
