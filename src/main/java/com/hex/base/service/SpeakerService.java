package com.hex.base.service;

import com.hex.base.domain.Speaker;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * User: hexuan
 * Date: 2018/7/6
 * Time: 下午4:40
 */
public interface SpeakerService {
    Speaker saveSpeaker(Speaker speaker);

    Speaker findSpeakerById(Integer id);

    List<Speaker> findAllSpeakerList(Sort sort);

    void deleteSpeakerById(Integer id);
}
