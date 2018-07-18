package com.hex.base.converter;

import com.hex.base.domain.Meeting;
import com.hex.base.vo.MeetingVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * User: hexuan
 * Date: 2018/7/11
 * Time: 上午10:30
 */
public class Meeting2MeetingVOConverter {
    public static MeetingVO converter(Meeting meeting) {
        MeetingVO meetingVO = new MeetingVO();
        BeanUtils.copyProperties(meeting, meetingVO);
        meetingVO.setStateStr(meeting.stateStr().getMsg());
        return meetingVO;
    }

    public static List<MeetingVO> converter(List<Meeting> meetingList) {
        List<MeetingVO> meetingVOList = new ArrayList<>();
        for (Meeting meeting : meetingList) {
            meetingVOList.add(converter(meeting));
        }
        return meetingVOList;
    }
}
