package com.hex.base.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * User: hexuan
 * Date: 2018/7/17
 * Time: 下午1:32
 */
@Data
public class SatelliteMeetingForm {

    private Integer id;

    /**
     * 会议id
     */
    @NotNull(message = "会议id不能为空")
    private Integer meetingId;

    /**
     * 图片
     */
    private MultipartFile imgFile;

    /**
     * 会议信息
     */
    private String info;
}
