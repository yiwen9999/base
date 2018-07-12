package com.hex.base.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;


/**
 * User: hexuan
 * Date: 2018/7/11
 * Time: 上午10:13
 */
@Data
public class CourseCategoryForm {

    private Integer id;

    /**
     * 课程分类名称
     */
    @NotBlank(message = "课程分类名称不能为空")
    private String name;

    /**
     * 课程分类排序
     */
    @NotNull(message = "课程分类排序号不能为空")
    private Integer sort;
}
