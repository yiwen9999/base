package com.hex.base.service;

import com.hex.base.domain.Schedule;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * User: hexuan
 * Date: 2018/7/6
 * Time: 下午4:34
 */
public interface ScheduleService {
    Schedule saveSchedule(Schedule schedule);

    Schedule findScheduleById(String id);

    List<Schedule> findAllScheduleList(Sort sort);

    void deleteScheduleById(String id);
}
