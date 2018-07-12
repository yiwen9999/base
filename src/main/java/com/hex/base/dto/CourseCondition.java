package com.hex.base.dto;

import lombok.Data;

import java.util.List;

/**
 * 课程查询条件bean
 * User: hexuan
 * Date: 2018/7/11
 * Time: 下午4:40
 */
@Data
public class CourseCondition {
    private List<Integer> courseCategoryIdList;
}
