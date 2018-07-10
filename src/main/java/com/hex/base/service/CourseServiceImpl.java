package com.hex.base.service;

import com.hex.base.domain.Course;
import com.hex.base.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * User: hexuan
 * Date: 2018/7/6
 * Time: 下午4:23
 */
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course findCourseById(Integer id) {
        return courseRepository.findOne(id);
    }

    @Override
    public Page<Course> findAllCourseList(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    @Override
    public void deleteCourseById(Integer id) {
        courseRepository.delete(id);
    }
}
