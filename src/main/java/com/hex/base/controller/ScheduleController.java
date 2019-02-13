package com.hex.base.controller;

import com.hex.base.converter.Schedule2ScheduleVOConverter;
import com.hex.base.domain.Schedule;
import com.hex.base.domain.Speaker;
import com.hex.base.dto.ScheduleCondition;
import com.hex.base.enums.ResultEnum;
import com.hex.base.exception.MyException;
import com.hex.base.form.ScheduleForm;
import com.hex.base.service.MeetingService;
import com.hex.base.service.ScheduleService;
import com.hex.base.service.SpeakerService;
import com.hex.base.util.HexUtil;
import com.hex.base.util.ResultUtil;
import com.hex.base.vo.ScheduleVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

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
        if (null != scheduleForm.getMeetingId() && !meetingService.findMeetingExist(scheduleForm.getMeetingId())) {
            return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), "会议id " + ResultEnum.ERROR_PARAM.getMsg());
        }

        Schedule schedule;
        if (StringUtils.isNotBlank(scheduleForm.getId())) {// 修改
            schedule = scheduleService.findScheduleById(scheduleForm.getId());
            if (null == schedule) {
                return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), "行程id " + ResultEnum.ERROR_PARAM.getMsg());
            }
        } else {// 新增
            schedule = new Schedule();
        }
        String scheduleId = schedule.getId();
        BeanUtils.copyProperties(scheduleForm, schedule);
        schedule.setId(scheduleId);
        if (null != scheduleForm.getSpeakerId()) {
            Speaker speaker = speakerService.findSpeakerById(schedule.getSpeakerId());
            if (null != speaker) {
                schedule.setSpeakerName(speaker.getName());
            }
        } else {
            schedule.setSpeakerName("");
        }

        return ResultUtil.success(scheduleService.saveSchedule(schedule).getId());
    }

    @PostMapping("/deleteSchedule")
    public Object deleteSchedule(String scheduleId) {
        Schedule schedule = scheduleService.findScheduleById(scheduleId);
        if (null != schedule) {
            scheduleService.deleteScheduleById(scheduleId);
            return ResultUtil.success();
        } else {
            return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), "日程id " + ResultEnum.ERROR_PARAM.getMsg());
        }
    }

    @PostMapping("/findScheduleListByCondition")
    public Object findScheduleByCondition(ScheduleCondition scheduleCondition,
                                          @RequestParam(defaultValue = "0") Integer page,
                                          @RequestParam(defaultValue = "20") Integer size,
                                          @RequestParam(defaultValue = "createTime") String sortStr,
                                          @RequestParam(defaultValue = "asc") String asc) {
        Pageable pageable = HexUtil.getPageRequest(page, size, sortStr, asc);
        Page<Schedule> schedulePage = scheduleService.findScheduleListByCondition(scheduleCondition, pageable);
        List<ScheduleVO> scheduleVOList = Schedule2ScheduleVOConverter.converter(schedulePage.getContent());
        return ResultUtil.success(new PageImpl<>(scheduleVOList, pageable, schedulePage.getTotalElements()));
    }

    @PostMapping("/findScheduleById")
    public Object findScheduleById(String scheduleId) {
        Schedule schedule = scheduleService.findScheduleById(scheduleId);
        if (null != schedule) {
            return ResultUtil.success(Schedule2ScheduleVOConverter.converter(schedule));
        } else {
            return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), "行程id " + ResultEnum.ERROR_PARAM.getMsg());
        }
    }

    @CrossOrigin
    @PostMapping("/findScheduleListViewByMeetingId")
    public Object findScheduleListViewByMeetingId(Integer meetingId) {
        return ResultUtil.success(scheduleService.findScheduleForMeetingVOListByMeetingId(meetingId));
    }

    @PostMapping("/updateScheduleSort")
    public Object updateScheduleSort(String scheduleId, Integer sort) {
        Schedule schedule = scheduleService.findScheduleById(scheduleId);
        if (null != schedule) {
            schedule.setSort(sort);
            return ResultUtil.success(Schedule2ScheduleVOConverter.converter(scheduleService.saveSchedule(schedule)));
        } else {
            return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), "行程id " + ResultEnum.ERROR_PARAM.getMsg());
        }
    }
}
