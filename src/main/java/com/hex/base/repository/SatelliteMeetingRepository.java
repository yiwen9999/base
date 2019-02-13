package com.hex.base.repository;

import com.hex.base.domain.SatelliteMeeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * User: hexuan
 * Date: 2018/7/10
 * Time: 下午2:27
 */
public interface SatelliteMeetingRepository extends JpaRepository<SatelliteMeeting, Integer> {

    List<SatelliteMeeting> findAllByMeetingIdOrderByCreateTime(Integer meetingId);

    void deleteAllByIdIn(List<Integer> idList);
}
