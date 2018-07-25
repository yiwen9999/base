package com.hex.base.controller;

import com.hex.base.domain.Schedule;
import com.hex.base.enums.ResultEnum;
import com.hex.base.exception.MyException;
import com.hex.base.form.ScheduleForm;
import com.hex.base.service.MeetingService;
import com.hex.base.service.ScheduleService;
import com.hex.base.service.SpeakerService;
import com.hex.base.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * User: hexuan
 * Date: 2018/7/24
 * Time: 下午4:49
 */
@RestController
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private SpeakerService speakerService;

    @PostMapping("/saveSchedule")
    public Object saveSchedule(@Valid ScheduleForm scheduleForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new MyException(ResultEnum.ERROR_PARAM.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        Schedule schedule = new Schedule();
        if (StringUtils.isNotBlank(schedule.getId())) {
            schedule = scheduleService.findScheduleById(scheduleForm.getId());
            if (null == schedule) {
                return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), "行程id " + ResultEnum.ERROR_PARAM.getMsg());
            }
        }
        BeanUtils.copyProperties(scheduleForm, schedule);

        if (null != schedule.getMeetingId() && !meetingService.findMeetingExist(schedule.getMeetingId())) {
            return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), "会议id " + ResultEnum.ERROR_PARAM.getMsg());
        }

        if (null != schedule.getSpeakerId() && !speakerService.findSpeakerExist(schedule.getSpeakerId())) {
            return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), "讲者id " + ResultEnum.ERROR_PARAM.getMsg());
        }

        return ResultUtil.success(scheduleService.saveSchedule(schedule).getId());
    }

    @PostMapping("/deleteSchedule")
    public Object deleteSchedule() {

        return ResultUtil.success();
    }
}
