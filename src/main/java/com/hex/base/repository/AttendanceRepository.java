package com.hex.base.repository;

import com.hex.base.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: hexuan
 * Date: 2018/7/6
 * Time: 下午4:06
 */
public interface AttendanceRepository extends JpaRepository<Attendance,String> {
}
