package com.hex.base.service;

import com.hex.base.domain.Course;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * User: hexuan
 * Date: 2018/7/6
 * Time: 下午4:22
 */
public interface CourseService {
    Course saveCourse(Course course);

    Course findCourseById(Integer id);

    List<Course> findAllCourseList(Sort sort);

    void deleteCourseById(Integer id);
}
