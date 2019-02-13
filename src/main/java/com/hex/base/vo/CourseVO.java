package com.hex.base.vo;

import lombok.Data;

/**
 * User: hexuan
 * Date: 2018/7/11
 * Time: 下午4:53
 */
@Data
public class CourseVO {

    private Integer id;

    private String name;

    private String subtitle;

    private String intro;

    private String videoPath;

    private String videoImg;

    private Integer courseCategoryId;

    private String courseCategoryName;
}
