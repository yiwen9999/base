package com.hex.base.controller;

import com.hex.base.converter.SatelliteMeeting2SatelliteMeetingVOConverter;
import com.hex.base.domain.SatelliteMeeting;
import com.hex.base.enums.DefaultImgNameEnum;
import com.hex.base.enums.ResultEnum;
import com.hex.base.exception.MyException;
import com.hex.base.form.SatelliteMeetingForm;
import com.hex.base.service.SatelliteMeetingService;
import com.hex.base.util.FileUtil;
import com.hex.base.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * User: hexuan
 * Date: 2018/7/17
 * Time: 下午1:29
 */
@RestController
public class SatelliteMeetingController {

    @Autowired
    private SatelliteMeetingService satelliteMeetingService;

    @Value("${web.upload-path}")
    private String path;

    @Value("${web.zip-file-limit}")
    private Long zipFileLimit;

    @PostMapping("/saveSatelliteMeeting")
    public Object saveSatelliteMeeting(@Valid SatelliteMeetingForm satelliteMeetingForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new MyException(ResultEnum.ERROR_PARAM.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        SatelliteMeeting satelliteMeeting = new SatelliteMeeting();
        if (null != satelliteMeetingForm.getId()) {
            satelliteMeeting = satelliteMeetingService.findSatelliteMeetingById(satelliteMeetingForm.getId());
            if (null == satelliteMeeting) {
                return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), "id " + ResultEnum.ERROR_PARAM.getMsg());
            }
        }
        BeanUtils.copyProperties(satelliteMeetingForm, satelliteMeeting);

        if (null != satelliteMeetingForm.getImgFile()) {
            try {
                satelliteMeeting.setImg(FileUtil.uploadImgFileNew(satelliteMeetingForm.getImgFile(), satelliteMeeting.getImg(), DefaultImgNameEnum.SATELLITE_MEETING_IMG.getImgName(), path, zipFileLimit));
            } catch (Exception e) {
                e.printStackTrace();
                return ResultUtil.error(ResultEnum.UPLOAD_FAIL.getCode(), ResultEnum.UPLOAD_FAIL.getMsg());
            }
        }

        return ResultUtil.success(satelliteMeetingService.saveSatelliteMeeting(satelliteMeeting).getId());
    }

    @PostMapping("/deleteSatelliteMeeting")
    public Object deleteSatelliteMeeting(Integer satelliteMeetingId) {
        SatelliteMeeting satelliteMeeting = satelliteMeetingService.findSatelliteMeetingById(satelliteMeetingId);
        if (null != satelliteMeeting) {
            if (StringUtils.isNotBlank(satelliteMeeting.getImg()) && !satelliteMeeting.getImg().equals(DefaultImgNameEnum.SATELLITE_MEETING_IMG.getImgName())) {
                FileUtil.deleteFile(path, satelliteMeeting.getImg());
            }
            satelliteMeetingService.deleteSatelliteMeetingById(satelliteMeetingId);
            return ResultUtil.success();
        } else {
            return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), "卫星会议id " + ResultEnum.ERROR_PARAM.getMsg());
        }
    }

    /**
     * 根据会议id查询
     *
     * @param meetingId
     * @return
     */
    @CrossOrigin
    @PostMapping("/findSatelliteMeetingListByMeetingId")
    public Object findSatelliteMeetingListByMeetingId(Integer meetingId) {
        List<SatelliteMeeting> satelliteMeetingList = satelliteMeetingService.findAllSatelliteMeetingListByMeetingIdOrderByCreateTime(meetingId);
        return ResultUtil.success(SatelliteMeeting2SatelliteMeetingVOConverter.converter(satelliteMeetingList));
    }

}
