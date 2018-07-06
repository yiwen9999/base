package com.hex.base.repository;

import com.hex.base.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: hexuan
 * Date: 2018/7/6
 * Time: 下午4:08
 */
public interface CourseRepository extends JpaRepository<Course,Integer> {
}
