package com.hex.base.repository;

import com.hex.base.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: hexuan
 * Date: 2018/7/6
 * Time: 下午4:09
 */
public interface MeetingRepository extends JpaRepository<Meeting, Integer> {
}
