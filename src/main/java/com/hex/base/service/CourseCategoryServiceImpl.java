package com.hex.base.service;

import com.hex.base.domain.CourseCategory;
import com.hex.base.dto.CourseCategoryCondition;
import com.hex.base.enums.ObjectStateEnum;
import com.hex.base.repository.CourseCategoryRepository;
import com.hex.base.repository.MySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * User: hexuan
 * Date: 2018/7/11
 * Time: 上午10:09
 */
@Service
public class CourseCategoryServiceImpl implements CourseCategoryService {

    @Autowired
    private CourseCategoryRepository courseCategoryRepository;

    @Override
    public CourseCategory saveCourseCategory(CourseCategory courseCategory) {
        return courseCategoryRepository.save(courseCategory);
    }

    @Override
    public CourseCategory findCourseCategoryById(Integer id) {
        return courseCategoryRepository.findOne(id);
    }

    @Override
    public Page<CourseCategory> findAllCourseCategoryList(Pageable pageable) {
        return courseCategoryRepository.findAll(pageable);
    }

    @Override
    public void deleteCourseCategoryById(Integer id) {
        courseCategoryRepository.delete(id);
    }

    @Override
    public Page<CourseCategory> findCourseCategoryListByCondition(CourseCategoryCondition courseCategoryCondition, Pageable pageable) {
        return courseCategoryRepository.findAll(MySpec.findCourseCategorys(courseCategoryCondition), pageable);
    }

    @Override
    public Page<CourseCategory> findUsingCourseCategoryList(Pageable pageable) {
        CourseCategoryCondition courseCategoryCondition = new CourseCategoryCondition();
        courseCategoryCondition.setStateList(Arrays.asList(ObjectStateEnum.START_USING.getCode()));
        return courseCategoryRepository.findAll(MySpec.findCourseCategorys(courseCategoryCondition), pageable);
    }

    @Override
    public CourseCategory updateCourseCategoryStateById(Integer id) {
        CourseCategory courseCategory = courseCategoryRepository.findOne(id);
        if (null != courseCategory) {
            if (courseCategory.getState() == ObjectStateEnum.BLOCK_UP.getCode()) {
                courseCategory.setState(ObjectStateEnum.START_USING.getCode());
            } else {
                courseCategory.setState(ObjectStateEnum.BLOCK_UP.getCode());
            }
            courseCategoryRepository.save(courseCategory);
        }
        return courseCategory;
    }
}
