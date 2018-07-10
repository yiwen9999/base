package com.hex.base.service;

import com.hex.base.domain.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * User: hexuan
 * Date: 2018/7/6
 * Time: 下午4:22
 */
public interface CourseService {
    Course saveCourse(Course course);

    Course findCourseById(Integer id);

    Page<Course> findAllCourseList(Pageable pageable);

    void deleteCourseById(Integer id);
}
