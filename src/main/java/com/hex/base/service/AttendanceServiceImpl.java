package com.hex.base.service;

import com.hex.base.domain.Attendance;
import com.hex.base.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * User: hexuan
 * Date: 2018/7/6
 * Time: 下午4:16
 */
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
    public List<Attendance> findAllAttendanceList(Sort sort) {
        return attendanceRepository.findAll(sort);
    }

    @Override
    public void deleteAttendanceById(String id) {
        attendanceRepository.delete(id);
    }
}
