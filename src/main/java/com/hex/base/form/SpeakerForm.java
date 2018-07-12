package com.hex.base.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;


/**
 * User: hexuan
 * Date: 2018/7/12
 * Time: 下午3:20
 */
@Data
public class SpeakerForm {

    private Integer id;

    /**
     * 姓名
     */
    @NotBlank(message = "讲者姓名不能为空")
    private String name;

    /**
     * 职称
     */
    private String professionalTitle;

    /**
     * 任职医院信息
     */
    private String hospitalInfo;

    /**
     * 简介
     */
    private String intro;

    /**
     * 照片
     */
    private MultipartFile photoFile;
}
