package com.hex.base.service;

import com.hex.base.domain.Speaker;
import com.hex.base.dto.SpeakerCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * User: hexuan
 * Date: 2018/7/6
 * Time: 下午4:40
 */
public interface SpeakerService {
    Speaker saveSpeaker(Speaker speaker);

    Speaker findSpeakerById(Integer id);

    Page<Speaker> findAllSpeakerList(Pageable pageable);

    void deleteSpeakerById(Integer id);

    Page<Speaker> findSpeakerListByCondition(SpeakerCondition speakerCondition, Pageable pageable);
}
