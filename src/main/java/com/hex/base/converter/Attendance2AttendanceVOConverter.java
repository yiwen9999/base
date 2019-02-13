package com.hex.base.converter;

import com.hex.base.domain.Attendance;
import com.hex.base.vo.AttendanceVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * User: hexuan
 * Date: 2018/10/18
 * Time: 10:54 AM
 */
public class Attendance2AttendanceVOConverter {
    public static AttendanceVO converter(Attendance attendance) {
        AttendanceVO attendanceVO = new AttendanceVO();
        BeanUtils.copyProperties(attendance, attendanceVO);
        return attendanceVO;
    }

    public static List<AttendanceVO> converter(List<Attendance> attendanceList) {
        List<AttendanceVO> attendanceVOList = new ArrayList<>();
        for (Attendance attendance : attendanceList) {
            attendanceVOList.add(converter(attendance));
        }
        return attendanceVOList;
    }
}
