package com.hex.base.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * User: hexuan
 * Date: 2018/7/11
 * Time: 下午4:27
 */
@Data
public class CourseForm {

    private Integer id;

    /**
     * 课程名称
     */
    @NotBlank(message = "课程名称不能为空")
    private String name;

    /**
     * 简介
     */
    @NotBlank(message = "课程简介不能为空")
    private String intro;

    /**
     * 视频路径
     */
    @NotBlank(message = "课程视频路径不能为空")
    private String videoPath;

    /**
     * 分类id
     */
    @NotNull(message = "分类id不能为空")
    private Integer courseCategoryId;
}
