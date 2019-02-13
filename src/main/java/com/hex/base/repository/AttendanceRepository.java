package com.hex.base.repository;

import com.hex.base.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * User: hexuan
 * Date: 2018/7/6
 * Time: 下午4:06
 */
public interface AttendanceRepository extends JpaRepository<Attendance, String>, JpaSpecificationExecutor {

    Attendance findFirstByMeetingIdAndPhone(Integer meetingId, String phone);

    List<Attendance> findAttendancesByMeetingIdOrderByCreateTimeAsc(Integer meetingId);
}
