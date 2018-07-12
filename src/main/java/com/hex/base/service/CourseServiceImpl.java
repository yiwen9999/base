package com.hex.base.service;

import com.hex.base.domain.Course;
import com.hex.base.dto.CourseCondition;
import com.hex.base.repository.CourseRepository;
import com.hex.base.repository.MySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * User: hexuan
 * Date: 2018/7/6
 * Time: 下午4:23
 */
@Service
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

    @Override
    public Page<Course> findCourseListByCondition(CourseCondition courseCondition, Pageable pageable) {
        return courseRepository.findAll(MySpec.findCourses(courseCondition), pageable);
    }
}
