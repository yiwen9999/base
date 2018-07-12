package com.hex.base.converter;

import com.hex.base.domain.Speaker;
import com.hex.base.vo.SpeakerVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * User: hexuan
 * Date: 2018/7/12
 * Time: 下午4:41
 */
public class Speaker2SpeakerVOConverter {
    public static SpeakerVO converter(Speaker speaker) {
        SpeakerVO speakerVO = new SpeakerVO();
        BeanUtils.copyProperties(speaker, speakerVO);
        return speakerVO;
    }

    public static List<SpeakerVO> converter(List<Speaker> speakerList) {
        List<SpeakerVO> speakerVOList = new ArrayList<>();
        for (Speaker speaker : speakerList) {
            speakerVOList.add(converter(speaker));
        }
        return speakerVOList;
    }
}
