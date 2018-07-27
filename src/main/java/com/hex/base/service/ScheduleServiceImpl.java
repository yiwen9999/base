package com.hex.base.service;

import com.hex.base.domain.Schedule;
import com.hex.base.dto.ScheduleCondition;
import com.hex.base.repository.MySpec;
import com.hex.base.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * User: hexuan
 * Date: 2018/7/6
 * Time: 下午4:37
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule findScheduleById(String id) {
        return scheduleRepository.findOne(id);
    }

    @Override
    public Page<Schedule> findAllScheduleList(Pageable pageable) {
        return scheduleRepository.findAll(pageable);
    }

    @Override
    public void deleteScheduleById(String id) {
        scheduleRepository.delete(id);
    }

    @Override
    public Page<Schedule> findScheduleListByCondition(ScheduleCondition scheduleCondition, Pageable pageable) {
        return scheduleRepository.findAll(MySpec.findSchedules(scheduleCondition), pageable);
    }
}
