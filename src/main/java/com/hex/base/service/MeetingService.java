package com.hex.base.service;

import com.hex.base.domain.Meeting;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * User: hexuan
 * Date: 2018/7/6
 * Time: 下午4:27
 */
public interface MeetingService {
    Meeting saveMeeting(Meeting meeting);

    Meeting findMeetingById(Integer id);

    List<Meeting> findAllMeetingList(Sort sort);

    void deleteMeetingById(Integer id);
}
