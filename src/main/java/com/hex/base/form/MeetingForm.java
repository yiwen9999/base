package com.hex.base.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

/**
 * User: hexuan
 * Date: 2018/7/13
 * Time: 上午10:20
 */
@Data
public class MeetingForm {

    private Integer id;

    /**
     * 会议名称
     */
    @NotBlank(message = "会议名称不能为空")
    private String name;

    /**
     * 地址信息
     */
    @NotBlank(message = "会议地址不能为空")
    private String address;

    /**
     * 日期信息
     */
    @NotBlank(message = "会议时间信息不能为空")
    private String timeStr;

    /**
     * 会议小贴士
     */
    private String tips;

    /**
     * 会议图片文件
     */
    private MultipartFile imgFile;

    /**
     * 会议logo文件
     */
    private MultipartFile logoFile;
}
