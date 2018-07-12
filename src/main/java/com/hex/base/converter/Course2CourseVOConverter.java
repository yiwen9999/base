package com.hex.base.converter;

import com.hex.base.domain.Course;
import com.hex.base.vo.CourseVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * User: hexuan
 * Date: 2018/7/11
 * Time: 下午4:56
 */
public class Course2CourseVOConverter {
    public static CourseVO converter(Course course) {
        CourseVO courseVO = new CourseVO();
        BeanUtils.copyProperties(course, courseVO);
        return courseVO;
    }

    public static List<CourseVO> converter(List<Course> courseList) {
        List<CourseVO> courseVOList = new ArrayList<>();
        for (Course course : courseList) {
            courseVOList.add(converter(course));
        }
        return courseVOList;
    }
}
