package com.hex.base.service;

import com.hex.base.domain.Attendance;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * User: hexuan
 * Date: 2018/7/6
 * Time: 下午4:13
 */
public interface AttendanceService {
    Attendance saveAttendance(Attendance attendance);

    Attendance findAttendanceById(String id);

    List<Attendance> findAllAttendanceList(Sort sort);

    void deleteAttendanceById(String id);
}
