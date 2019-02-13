package com.hex.base.controller;

import com.hex.base.converter.Attendance2AttendanceVOConverter;
import com.hex.base.domain.Attendance;
import com.hex.base.domain.UserInfo;
import com.hex.base.dto.AttendanceCondition;
import com.hex.base.enums.ResultEnum;
import com.hex.base.exception.MyException;
import com.hex.base.form.AttendanceForm;
import com.hex.base.service.AttendanceService;
import com.hex.base.service.UserInfoService;
import com.hex.base.util.HexUtil;
import com.hex.base.util.ResultUtil;
import com.hex.base.vo.AttendanceVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * User: hexuan
 * Date: 2018/10/16
 * Time: 2:52 PM
 */
@RestController
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private UserInfoService userInfoService;

    @CrossOrigin
    @PostMapping("/saveAttendance")
    public Object saveAttendance(@Valid AttendanceForm attendanceForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new MyException(ResultEnum.ERROR_PARAM.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        Attendance attendance = new Attendance();
        BeanUtils.copyProperties(attendanceForm, attendance);

        /**
         * TODO 目前根据phone查询用户，后期配合网页授权后应改为通过userInfoId获取
         */
        UserInfo userInfo = null;
        if (StringUtils.isNotBlank(attendanceForm.getPhone())) {
            userInfo = userInfoService.findUserInfoByPhone(attendanceForm.getPhone());
            if (null != userInfo) {
                userInfo.setName(attendance.getName());
                userInfo.setPhone(attendance.getPhone());
                userInfo.setHospital(attendance.getHospital());
                userInfo.setPlaceName(attendance.getPlaceName());
                userInfo.setPlaceId(attendance.getPlaceId());
                userInfo.setAttendanceTimes(userInfo.getAttendanceTimes() + 1);
                userInfoService.saveUserInfo(userInfo);
            } else {
                userInfo = new UserInfo();

                userInfo.setName(attendance.getName());
                userInfo.setPhone(attendance.getPhone());
                userInfo.setHospital(attendance.getHospital());
                userInfo.setPlaceName(attendance.getPlaceName());
                userInfo.setPlaceId(attendance.getPlaceId());

                userInfo.setAttendanceTimes(userInfo.getAttendanceTimes() + 1);
                userInfoService.saveUserInfo(userInfo);
            }
        }

        if (null != userInfo)
            attendance.setUserInfoId(userInfo.getId());
        attendanceService.saveAttendance(attendance);

        return ResultUtil.success(attendance.getId());
    }

    @PostMapping("/findAttendanceListByCondition")
    public Object findAttendanceListByCondition(AttendanceCondition attendanceCondition,
                                                @RequestParam(defaultValue = "0") Integer page,
                                                @RequestParam(defaultValue = "20") Integer size,
                                                @RequestParam(defaultValue = "createTime") String sortStr,
                                                @RequestParam(defaultValue = "desc") String asc) {
        return ResultUtil.success(attendanceService.findAttendanceListByCondition(attendanceCondition, HexUtil.getPageRequest(page, size, sortStr, asc)));
    }

    @CrossOrigin
    @PostMapping("/updateAttendanceWinningRecord")
    public Object updateAttendanceWinningRecord(String attendanceId, Boolean winningRecord) {
        Attendance attendance = attendanceService.findAttendanceById(attendanceId);
        if (null != attendance) {
            attendance.setWinningRecord(winningRecord);
            return ResultUtil.success(Attendance2AttendanceVOConverter.converter(attendanceService.saveAttendance(attendance)));
        } else {
            return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), "签到id " + ResultEnum.ERROR_PARAM.getMsg());
        }
    }

    @CrossOrigin
    @PostMapping("/validateAttendance")
    public Object validateAttendance(Integer meetingId, String phone) {
        /**
         * true 是同会议下未存在签到记录，可以签到。
         * false 是同会议下已存在签到记录，重复签到。
         */
        return ResultUtil.success(attendanceService.validateAttendanceByMeetingIdAndPhone(meetingId, phone));
    }

    @CrossOrigin
    @PostMapping("/findAttendanceById")
    public Object findAttendanceById(String attendanceId) {
        Attendance attendance = attendanceService.findAttendanceById(attendanceId);
        AttendanceVO attendanceVO = null;
        if (null != attendance) {
            attendanceVO = Attendance2AttendanceVOConverter.converter(attendance);
        }
        return ResultUtil.success(attendanceVO);
    }
}
