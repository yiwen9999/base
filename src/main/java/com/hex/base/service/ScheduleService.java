package com.hex.base.service;

import com.hex.base.domain.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * User: hexuan
 * Date: 2018/7/6
 * Time: 下午4:34
 */
public interface ScheduleService {
    Schedule saveSchedule(Schedule schedule);

    Schedule findScheduleById(String id);

    Page<Schedule> findAllScheduleList(Pageable pageable);

    void deleteScheduleById(String id);
}
