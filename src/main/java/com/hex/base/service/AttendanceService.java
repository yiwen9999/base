package com.hex.base.service;

import com.hex.base.domain.Attendance;
import com.hex.base.dto.AttendanceCondition;
import com.hex.base.vo.AttendanceVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    Page<Attendance> findAllAttendanceList(Pageable pageable);

    void deleteAttendanceById(String id);

    Page<AttendanceVO> findAttendanceListByCondition(AttendanceCondition attendanceCondition, Pageable pageable);

    Boolean validateAttendanceByMeetingIdAndPhone(Integer meetingId, String phone);

    List<Attendance> findAllAttendanceList(Sort sort);

    List<Attendance> findAttendanceListByMeetingId(Integer meetingId);
}
