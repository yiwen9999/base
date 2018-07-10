package com.hex.base.service;

import com.hex.base.domain.Meeting;
import com.hex.base.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * User: hexuan
 * Date: 2018/7/6
 * Time: 下午4:29
 */
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
}
