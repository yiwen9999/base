package com.hex.base.converter;

import com.hex.base.domain.Course;
import com.hex.base.domain.CourseCategory;
import com.hex.base.vo.CourseVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: hexuan
 * Date: 2018/7/11
 * Time: 下午4:56
 */
public class Course2CourseVOConverter {
    public static CourseVO converter(Course course, Map<Integer, CourseCategory> courseCategoryMap) {
        CourseVO courseVO = new CourseVO();
        BeanUtils.copyProperties(course, courseVO);
        courseVO.setCourseCategoryName(courseCategoryMap.get(courseVO.getCourseCategoryId()).getName());
        return courseVO;
    }

    public static List<CourseVO> converter(List<Course> courseList, Map<Integer, CourseCategory> courseCategoryMap) {
        List<CourseVO> courseVOList = new ArrayList<>();
        for (Course course : courseList) {
            courseVOList.add(converter(course, courseCategoryMap));
        }
        return courseVOList;
    }
}
