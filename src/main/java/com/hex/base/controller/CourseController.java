package com.hex.base.controller;

import com.hex.base.converter.Course2CourseVOConverter;
import com.hex.base.domain.Course;
import com.hex.base.dto.CourseCondition;
import com.hex.base.enums.ResultEnum;
import com.hex.base.exception.MyException;
import com.hex.base.form.CourseForm;
import com.hex.base.service.CourseService;
import com.hex.base.util.HexUtil;
import com.hex.base.util.ResultUtil;
import com.hex.base.vo.CourseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 课程视频controller
 * <p>
 * User: hexuan
 * Date: 2018/7/11
 * Time: 下午4:26
 */
@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/saveCourse")
    public Object saveCourse(@Valid CourseForm courseForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new MyException(ResultEnum.ERROR_PARAM.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        Course course = new Course();
        if (null != courseForm.getId()) {
            course = courseService.findCourseById(courseForm.getId());
            if (null == course) {
                return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), "id " + ResultEnum.ERROR_PARAM.getMsg());
            }
        }
        BeanUtils.copyProperties(courseForm, course);
        return ResultUtil.success(courseService.saveCourse(course).getId());
    }

    @PostMapping("/findCourseListByCondition")
    public Object findCourseListByCondition(CourseCondition courseCondition,
                                            @RequestParam(defaultValue = "0") Integer page,
                                            @RequestParam(defaultValue = "20") Integer size,
                                            @RequestParam(defaultValue = "createTime") String sortStr,
                                            @RequestParam(defaultValue = "desc") String asc) {
        Pageable pageable = HexUtil.getPageRequest(page, size, sortStr, asc);
        Page<Course> coursePage = courseService.findCourseListByCondition(courseCondition, pageable);
        List<CourseVO> courseVOList = Course2CourseVOConverter.converter(coursePage.getContent());
        return ResultUtil.success(new PageImpl<>(courseVOList, pageable, coursePage.getTotalElements()));
    }

    @PostMapping("/deleteCourse")
    public Object deleteCourse(Integer courseId) {
        courseService.deleteCourseById(courseId);
        return ResultUtil.success();
    }
}
