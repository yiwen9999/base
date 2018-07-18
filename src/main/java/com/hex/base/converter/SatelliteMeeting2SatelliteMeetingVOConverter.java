package com.hex.base.converter;

import com.hex.base.domain.SatelliteMeeting;
import com.hex.base.vo.SatelliteMeetingVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * User: hexuan
 * Date: 2018/7/17
 * Time: 下午2:23
 */
public class SatelliteMeeting2SatelliteMeetingVOConverter {
    public static SatelliteMeetingVO converter(SatelliteMeeting satelliteMeeting) {
        SatelliteMeetingVO satelliteMeetingVO = new SatelliteMeetingVO();
        BeanUtils.copyProperties(satelliteMeeting, satelliteMeetingVO);
        return satelliteMeetingVO;
    }

    public static List<SatelliteMeetingVO> converter(List<SatelliteMeeting> satelliteMeetingList) {
        List<SatelliteMeetingVO> satelliteMeetingVOList = new ArrayList<>();
        for (SatelliteMeeting satelliteMeeting : satelliteMeetingList) {
            satelliteMeetingVOList.add(converter(satelliteMeeting));
        }
        return satelliteMeetingVOList;
    }
}
