package com.hex.base.service;

import com.hex.base.domain.Meeting;
import com.hex.base.dto.MeetingCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * User: hexuan
 * Date: 2018/7/6
 * Time: 下午4:27
 */
public interface MeetingService {
    Meeting saveMeeting(Meeting meeting);

    Meeting findMeetingById(Integer id);

    Page<Meeting> findAllMeetingList(Pageable pageable);

    void deleteMeetingById(Integer id);

    Page<Meeting> findMeetingListByCondition(MeetingCondition meetingCondition, Pageable pageable);

    Meeting updateMeetingState(Integer meetingId, Integer state);

    /**
     * 根据id判断会议是否存在
     *
     * @param id
     * @return
     */
    Boolean findMeetingExist(Integer id);
}
