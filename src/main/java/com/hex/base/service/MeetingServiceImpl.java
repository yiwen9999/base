package com.hex.base.service;

import com.hex.base.domain.Meeting;
import com.hex.base.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

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
    public List<Meeting> findAllMeetingList(Sort sort) {
        return meetingRepository.findAll(sort);
    }

    @Override
    public void deleteMeetingById(Integer id) {
        meetingRepository.delete(id);
    }
}
