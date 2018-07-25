package com.hex.base.service;

import com.hex.base.domain.Speaker;
import com.hex.base.dto.SpeakerCondition;
import com.hex.base.repository.MySpec;
import com.hex.base.repository.SpeakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * User: hexuan
 * Date: 2018/7/6
 * Time: 下午4:43
 */
@Service
public class SpeakerServiceImpl implements SpeakerService {

    @Autowired
    private SpeakerRepository speakerRepository;

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
}
