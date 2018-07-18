package com.hex.base.controller;

import com.hex.base.converter.Meeting2MeetingVOConverter;
import com.hex.base.domain.Meeting;
import com.hex.base.dto.MeetingCondition;
import com.hex.base.enums.DefaultImgNameEnum;
import com.hex.base.enums.ResultEnum;
import com.hex.base.exception.MyException;
import com.hex.base.form.MeetingForm;
import com.hex.base.service.MeetingService;
import com.hex.base.util.FileUtil;
import com.hex.base.util.HexUtil;
import com.hex.base.util.ResultUtil;
import com.hex.base.vo.MeetingVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 会议controller
 * User: hexuan
 * Date: 2018/7/13
 * Time: 上午10:17
 */
@RestController
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @Value("${web.upload-path}")
    private String path;

    @Value("${web.zip-file-limit}")
    private Long zipFileLimit;

    @PostMapping("/saveMeeting")
    public Object saveMeeting(@Valid MeetingForm meetingForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new MyException(ResultEnum.ERROR_PARAM.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        Meeting meeting = new Meeting();
        if (null != meetingForm.getId()) {
            meeting = meetingService.findMeetingById(meetingForm.getId());
        }
        BeanUtils.copyProperties(meetingForm, meeting);

        if (null != meetingForm.getImgFile()) {
            try {
                meeting.setImg(FileUtil.uploadImgFileNew(meetingForm.getImgFile(), meeting.getImg(), DefaultImgNameEnum.MEETING_IMG.getImgName(), path, zipFileLimit));
            } catch (Exception e) {
                e.printStackTrace();
                return ResultUtil.error(ResultEnum.UPLOAD_FAIL.getCode(), ResultEnum.UPLOAD_FAIL.getMsg());
            }
        }

        if (null != meetingForm.getLogoFile()) {
            try {
                meeting.setLogo(FileUtil.uploadImgFileNew(meetingForm.getLogoFile(), meeting.getLogo(), DefaultImgNameEnum.MEETING_LOGO.getImgName(), path, zipFileLimit));
            } catch (Exception e) {
                e.printStackTrace();
                return ResultUtil.error(ResultEnum.UPLOAD_FAIL.getCode(), ResultEnum.UPLOAD_FAIL.getMsg());
            }
        }

        return ResultUtil.success(meetingService.saveMeeting(meeting).getId());
    }

    @PostMapping("/deleteMeeting")
    public Object deleteMeeting(Integer meetingId) {
        Meeting meeting = meetingService.findMeetingById(meetingId);
        if (null != meeting) {
            if (StringUtils.isNotBlank(meeting.getImg()) && !meeting.getImg().equals(DefaultImgNameEnum.MEETING_IMG.getImgName())) {
                FileUtil.deleteFile(path, meeting.getImg());
            }
            if (StringUtils.isNotBlank(meeting.getLogo()) && !meeting.getImg().equals(DefaultImgNameEnum.MEETING_LOGO.getImgName())) {
                FileUtil.deleteFile(path, meeting.getLogo());
            }
            meetingService.deleteMeetingById(meetingId);
            return ResultUtil.success();
        } else {
            return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), "会议id " + ResultEnum.ERROR_PARAM.getMsg());
        }
    }

    @PostMapping("/findMeetingListByCondition")
    public Object findMeetingListByCondition(MeetingCondition meetingCondition,
                                             @RequestParam(defaultValue = "0") Integer page,
                                             @RequestParam(defaultValue = "50") Integer size,
                                             @RequestParam(defaultValue = "createTime") String sortStr,
                                             @RequestParam(defaultValue = "desc") String asc) {
        Pageable pageable = HexUtil.getPageRequest(page, size, sortStr, asc);
        Page<Meeting> meetingPage = meetingService.findMeetingListByCondition(meetingCondition, pageable);
        List<MeetingVO> meetingVOList = Meeting2MeetingVOConverter.converter(meetingPage.getContent());
        return ResultUtil.success(new PageImpl<>(meetingVOList, pageable, meetingPage.getTotalElements()));
    }

    @PostMapping("/updateMeetingState")
    public Object updateMeetingState(Integer meetingId, Integer state) {
        Meeting meeting = meetingService.updateMeetingState(meetingId, state);
        if (null != meeting) {
            return ResultUtil.success(Meeting2MeetingVOConverter.converter(meeting));
        } else {
            return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), "会议id " + ResultEnum.ERROR_PARAM.getMsg());
        }
    }
}
