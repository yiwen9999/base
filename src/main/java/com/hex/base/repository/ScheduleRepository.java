package com.hex.base.repository;

import com.hex.base.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


/**
 * User: hexuan
 * Date: 2018/7/6
 * Time: 下午4:10
 */
public interface ScheduleRepository extends JpaRepository<Schedule, String>, JpaSpecificationExecutor {
    List<Schedule> findAllByMeetingIdOrderByTimeAscSortAscCreateTimeAsc(Integer meetingId);

    void deleteAllByIdIn(List<String> idList);
}
