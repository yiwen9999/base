package com.hex.base.repository;

import com.hex.base.domain.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * User: hexuan
 * Date: 2018/7/11
 * Time: 上午10:05
 */
public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Integer>, JpaSpecificationExecutor {
}
