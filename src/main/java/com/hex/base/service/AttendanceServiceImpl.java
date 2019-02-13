package com.hex.base.service;

import com.hex.base.converter.Attendance2AttendanceVOConverter;
import com.hex.base.domain.Attendance;
import com.hex.base.dto.AttendanceCondition;
import com.hex.base.repository.AttendanceRepository;
import com.hex.base.repository.MySpec;
import com.hex.base.vo.AttendanceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: hexuan
 * Date: 2018/7/6
 * Time: 下午4:16
 */
@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Override
    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public Attendance findAttendanceById(String id) {
        return attendanceRepository.findOne(id);
    }

    @Override
    public Page<Attendance> findAllAttendanceList(Pageable pageable) {
        return attendanceRepository.findAll(pageable);
    }

    @Override
    public void deleteAttendanceById(String id) {
        attendanceRepository.delete(id);
    }

    @Override
    public Page<AttendanceVO> findAttendanceListByCondition(AttendanceCondition attendanceCondition, Pageable pageable) {
        Page<Attendance> attendancePage = attendanceRepository.findAll(MySpec.findAttendances(attendanceCondition), pageable);
        List<AttendanceVO> attendanceVOList = Attendance2AttendanceVOConverter.converter(attendancePage.getContent());
        return new PageImpl<>(attendanceVOList, pageable, attendancePage.getTotalElements());
    }

    @Override
    public Boolean validateAttendanceByMeetingIdAndPhone(Integer meetingId, String phone) {
        Attendance attendance = attendanceRepository.findFirstByMeetingIdAndPhone(meetingId, phone);
        if (null != attendance) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<Attendance> findAllAttendanceList(Sort sort) {
        return attendanceRepository.findAll(sort);
    }

    @Override
    public List<Attendance> findAttendanceListByMeetingId(Integer meetingId) {
        return attendanceRepository.findAttendancesByMeetingIdOrderByCreateTimeAsc(meetingId);
    }
}
