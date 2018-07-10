package com.hex.base.service;

import com.hex.base.domain.Attendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * User: hexuan
 * Date: 2018/7/6
 * Time: 下午4:13
 */
public interface AttendanceService {
    Attendance saveAttendance(Attendance attendance);

    Attendance findAttendanceById(String id);

    Page<Attendance> findAllAttendanceList(Pageable pageable);

    void deleteAttendanceById(String id);
}
