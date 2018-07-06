package com.hex.base.service;

import com.hex.base.domain.Speaker;
import com.hex.base.repository.SpeakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * User: hexuan
 * Date: 2018/7/6
 * Time: 下午4:43
 */
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
    public List<Speaker> findAllSpeakerList(Sort sort) {
        return speakerRepository.findAll(sort);
    }

    @Override
    public void deleteSpeakerById(Integer id) {
        speakerRepository.delete(id);
    }
}
