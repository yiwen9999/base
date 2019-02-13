package com.hex.base.controller;

import com.hex.base.converter.Speaker2SpeakerVOConverter;
import com.hex.base.domain.Speaker;
import com.hex.base.dto.SpeakerCondition;
import com.hex.base.enums.DefaultImgNameEnum;
import com.hex.base.enums.ResultEnum;
import com.hex.base.exception.MyException;
import com.hex.base.form.SpeakerForm;
import com.hex.base.service.SpeakerService;
import com.hex.base.util.FileUtil;
import com.hex.base.util.HexUtil;
import com.hex.base.util.ResultUtil;
import com.hex.base.vo.SpeakerVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
 * Date: 2018/7/12
 * Time: 下午3:24
 */
@RestController
public class SpeakerController {

    @Autowired
    private SpeakerService speakerService;

    @Value("${web.upload-path}")
    private String path;

    @Value("${web.zip-file-limit}")
    private Long zipFileLimit;

    /**
     * 保存&修改讲者
     *
     * @param speakerForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/saveSpeaker")
    public Object saveSpeaker(@Valid SpeakerForm speakerForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new MyException(ResultEnum.ERROR_PARAM.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        Speaker speaker = new Speaker();
        if (null != speakerForm.getId()) {
            speaker = speakerService.findSpeakerById(speakerForm.getId());
            if (null == speaker) {
                return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), "id " + ResultEnum.ERROR_PARAM.getMsg());
            }
        }
        BeanUtils.copyProperties(speakerForm, speaker);

        if (null != speakerForm.getPhotoFile()) {
            try {
                speaker.setPhoto(FileUtil.uploadImgFileNew(speakerForm.getPhotoFile(), speaker.getPhoto(), DefaultImgNameEnum.SPEAKER_PHOTO.getImgName(), path, zipFileLimit));
            } catch (Exception e) {
                e.printStackTrace();
                return ResultUtil.error(ResultEnum.UPLOAD_FAIL.getCode(), ResultEnum.UPLOAD_FAIL.getMsg());
            }
        }

        return ResultUtil.success(speakerService.saveSpeaker(speaker).getId());
    }

    @PostMapping("/findSpeakerListByCondition")
    public Object findSpeakerListByCondition(SpeakerCondition speakerCondition,
                                             @RequestParam(defaultValue = "0") Integer page,
                                             @RequestParam(defaultValue = "20") Integer size,
                                             @RequestParam(defaultValue = "createTime") String sortStr,
                                             @RequestParam(defaultValue = "desc") String asc) {
        Pageable pageable = HexUtil.getPageRequest(page, size, sortStr, asc);
        Page<Speaker> speakerPage = speakerService.findSpeakerListByCondition(speakerCondition, pageable);
        List<SpeakerVO> speakerVOList = Speaker2SpeakerVOConverter.converter(speakerPage.getContent());
        return ResultUtil.success(new PageImpl<>(speakerVOList, pageable, speakerPage.getTotalElements()));
    }

    @PostMapping("/deleteSpeaker")
    public Object deleteSpeaker(Integer speakerId) {
        Speaker speaker = speakerService.findSpeakerById(speakerId);
        if (null != speaker) {
            if (StringUtils.isNotBlank(speaker.getPhoto()) && !speaker.getPhoto().equals(DefaultImgNameEnum.SPEAKER_PHOTO.getImgName())) {
                FileUtil.deleteFile(path, speaker.getPhoto());
            }
            speakerService.deleteSpeakerById(speakerId);
            return ResultUtil.success();
        } else {
            return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), "讲者id " + ResultEnum.ERROR_PARAM.getMsg());
        }
    }

    @PostMapping("/findSpeakerById")
    public Object findSpeakerById(Integer speakerId) {
        Speaker speaker = speakerService.findSpeakerById(speakerId);
        if (null != speaker) {
            return ResultUtil.success(Speaker2SpeakerVOConverter.converter(speaker));
        } else {
            return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), "讲者id " + ResultEnum.ERROR_PARAM.getMsg());
        }
    }

    @CrossOrigin
    @PostMapping("/findSpeakerListByMeetingId")
    public Object findSpeakerListByMeetingId(Integer meetingId) {
        if (null != meetingId) {
            return ResultUtil.success(speakerService.findSpeakerVOListByMeetingId(meetingId));
        } else {
            return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), "讲者id " + ResultEnum.ERROR_PARAM.getMsg());
        }
    }

}
