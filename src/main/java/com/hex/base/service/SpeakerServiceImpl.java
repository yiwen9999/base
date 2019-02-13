package com.hex.base.service;

import com.hex.base.converter.Speaker2SpeakerVOConverter;
import com.hex.base.domain.Schedule;
import com.hex.base.domain.Speaker;
import com.hex.base.dto.SpeakerCondition;
import com.hex.base.repository.MySpec;
import com.hex.base.repository.ScheduleRepository;
import com.hex.base.repository.SpeakerRepository;
import com.hex.base.vo.SpeakerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User: hexuan
 * Date: 2018/7/6
 * Time: 下午4:43
 */
@Service
public class SpeakerServiceImpl implements SpeakerService {

    @Autowired
    private SpeakerRepository speakerRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public Speaker saveSpeaker(Speaker speaker) {
        return speakerRepository.save(speaker);
    }

    @Override
    public Speaker findSpeakerById(Integer id) {
        return speakerRepository.findOne(id);
    }

    @Override
    public Page<Speaker> findAllSpeakerList(Pageable pageable) {
        return speakerRepository.findAll(pageable);
    }

    @Override
    public void deleteSpeakerById(Integer id) {
        speakerRepository.delete(id);
    }

    @Override
    public Page<Speaker> findSpeakerListByCondition(SpeakerCondition speakerCondition, Pageable pageable) {
        return speakerRepository.findAll(MySpec.findSpeakers(speakerCondition), pageable);
    }

    @Override
    public Boolean findSpeakerExist(Integer id) {
        return speakerRepository.exists(id);
    }

    @Override
    public List<SpeakerVO> findSpeakerVOListByMeetingId(Integer meetingId) {
        List<Schedule> scheduleList = scheduleRepository.findAllByMeetingIdOrderByTimeAscSortAscCreateTimeAsc(meetingId);
        List<Integer> speakerIdList = new ArrayList<>();
        for (Schedule schedule : scheduleList) {
            if (null != schedule.getSpeakerId())
                speakerIdList.add(schedule.getSpeakerId());
        }
        List<Speaker> speakerList = speakerRepository.findSpeakersByIdInOrderById(speakerIdList);
        return Speaker2SpeakerVOConverter.converter(speakerList);
    }
}
