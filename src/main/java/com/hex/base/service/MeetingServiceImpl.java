package com.hex.base.service;

import com.hex.base.domain.Meeting;
import com.hex.base.dto.MeetingCondition;
import com.hex.base.repository.MeetingRepository;
import com.hex.base.repository.MySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * User: hexuan
 * Date: 2018/7/6
 * Time: 下午4:29
 */
@Service
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    private MeetingRepository meetingRepository;

    @Override
    public Meeting saveMeeting(Meeting meeting) {
        return meetingRepository.save(meeting);
    }

    @Override
    public Meeting findMeetingById(Integer id) {
        return meetingRepository.findOne(id);
    }

    @Override
    public Page<Meeting> findAllMeetingList(Pageable pageable) {
        return meetingRepository.findAll(pageable);
    }

    @Override
    public void deleteMeetingById(Integer id) {
        meetingRepository.delete(id);
    }

    @Override
    public Page<Meeting> findMeetingListByCondition(MeetingCondition meetingCondition, Pageable pageable) {
        return meetingRepository.findAll(MySpec.findMeetings(meetingCondition), pageable);
    }

    @Override
    public Meeting updateMeetingState(Integer meetingId, Integer state) {
        Meeting meeting = meetingRepository.findOne(meetingId);
        if (null != meeting) {
            meeting.setState(state);
            return meetingRepository.save(meeting);
        } else {
            return null;
        }
    }
}
