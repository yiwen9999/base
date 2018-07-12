package com.hex.base.service;

import com.hex.base.domain.CourseCategory;
import com.hex.base.dto.CourseCategoryCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * User: hexuan
 * Date: 2018/7/11
 * Time: 上午10:07
 */
public interface CourseCategoryService {
    CourseCategory saveCourseCategory(CourseCategory courseCategory);

    CourseCategory findCourseCategoryById(Integer id);

    Page<CourseCategory> findAllCourseCategoryList(Pageable pageable);

    void deleteCourseCategoryById(Integer id);

    Page<CourseCategory> findCourseCategoryListByCondition(CourseCategoryCondition courseCategoryCondition, Pageable pageable);

    Page<CourseCategory> findUsingCourseCategoryList(Pageable pageable);

    CourseCategory updateCourseCategoryStateById(Integer id);
}
