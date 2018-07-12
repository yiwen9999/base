package com.hex.base.converter;

import com.hex.base.domain.CourseCategory;
import com.hex.base.vo.CourseCategoryVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * User: hexuan
 * Date: 2018/7/11
 * Time: 上午10:30
 */
public class CourseCategory2CourseCategoryVOConverter {
    public static CourseCategoryVO converter(CourseCategory courseCategory) {
        CourseCategoryVO courseCategoryVO = new CourseCategoryVO();
        BeanUtils.copyProperties(courseCategory, courseCategoryVO);
        courseCategoryVO.setStateStr(courseCategory.stateStr().getMsg());
        return courseCategoryVO;
    }

    public static List<CourseCategoryVO> converter(List<CourseCategory> courseCategoryList) {
        List<CourseCategoryVO> courseCategoryVOList = new ArrayList<>();
        for (CourseCategory courseCategory : courseCategoryList) {
            courseCategoryVOList.add(converter(courseCategory));
        }
        return courseCategoryVOList;
    }
}
