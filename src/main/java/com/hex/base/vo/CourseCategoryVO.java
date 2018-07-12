package com.hex.base.vo;

import lombok.Data;

/**
 * User: hexuan
 * Date: 2018/7/11
 * Time: 上午10:16
 */
@Data
public class CourseCategoryVO {

    private Integer id;

    /**
     * 课程分类名称
     */
    private String name;

    /**
     * 课程分类状态值
     */
    private Integer state;

    /**
     * 课程分类状态
     */
    private String stateStr;

    /**
     * 课程分类排序
     */
    private Integer sort;
}
