package com.hex.base.service;

import com.hex.base.converter.Schedule2ScheduleVOConverter;
import com.hex.base.domain.Schedule;
import com.hex.base.dto.ScheduleCondition;
import com.hex.base.repository.MySpec;
import com.hex.base.repository.ScheduleRepository;
import com.hex.base.vo.ScheduleForMeetingVO;
import com.hex.base.vo.ScheduleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

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

    @Override
    public List<ScheduleForMeetingVO> findScheduleForMeetingVOListByMeetingId(Integer meetingId) {
        List<Schedule> scheduleList = scheduleRepository.findAllByMeetingIdOrderByTimeAscSortAscCreateTimeAsc(meetingId);
        List<ScheduleForMeetingVO> scheduleForMeetingVOList = new ArrayList<>();
        ScheduleVO scheduleVO;
        ScheduleForMeetingVO scheduleForMeetingVO;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Map<Date, List<ScheduleVO>> map = new LinkedHashMap<>();
        List<ScheduleVO> scheduleVOList;

        for (Schedule schedule : scheduleList) {
            scheduleVO = Schedule2ScheduleVOConverter.converter(schedule);

            if (null != map.get(schedule.getTime())) {
                map.get(schedule.getTime()).add(scheduleVO);
            } else {
                scheduleVOList = new ArrayList<>();
                scheduleVOList.add(scheduleVO);
                map.put(schedule.getTime(), scheduleVOList);
            }
        }

        for (Date time : map.keySet()) {
            scheduleForMeetingVO = new ScheduleForMeetingVO();
            scheduleForMeetingVO.setTimeStr(simpleDateFormat.format(time));
            scheduleForMeetingVO.setScheduleVOList(map.get(time));
            scheduleForMeetingVOList.add(scheduleForMeetingVO);
        }

        return scheduleForMeetingVOList;
    }
}
