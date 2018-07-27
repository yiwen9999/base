package com.hex.base.converter;

import com.hex.base.domain.Schedule;
import com.hex.base.vo.ScheduleVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * User: hexuan
 * Date: 2018/7/27
 * Time: 上午11:14
 */
public class Schedule2ScheduleVOConverter {
    public static ScheduleVO converter(Schedule schedule) {
        ScheduleVO scheduleVO = new ScheduleVO();
        BeanUtils.copyProperties(schedule, scheduleVO);
        return scheduleVO;
    }

    public static List<ScheduleVO> converter(List<Schedule> scheduleList) {
        List<ScheduleVO> scheduleVOList = new ArrayList<>();
        for (Schedule schedule : scheduleList) {
            scheduleVOList.add(converter(schedule));
        }
        return scheduleVOList;
    }
}
