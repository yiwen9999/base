package com.hex.base.controller;

import com.hex.base.converter.Course2CourseVOConverter;
import com.hex.base.domain.Course;
import com.hex.base.domain.CourseCategory;
import com.hex.base.dto.CourseCondition;
import com.hex.base.enums.DefaultImgNameEnum;
import com.hex.base.enums.ResultEnum;
import com.hex.base.exception.MyException;
import com.hex.base.form.CourseForm;
import com.hex.base.service.CourseCategoryService;
import com.hex.base.service.CourseService;
import com.hex.base.util.FileUtil;
import com.hex.base.util.HexUtil;
import com.hex.base.util.ResultUtil;
import com.hex.base.vo.CourseVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private CourseCategoryService courseCategoryService;

    @Value("${web.upload-path}")
    private String path;

    @Value("${web.zip-file-limit}")
    private Long zipFileLimit;

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

        if (null != courseForm.getVideoImgFile()) {
            try {
                course.setVideoImg(FileUtil.uploadImgFileNew(courseForm.getVideoImgFile(), course.getVideoImg(), DefaultImgNameEnum.VIDEO_IMG.getImgName(), path, zipFileLimit));
            } catch (Exception e) {
                e.printStackTrace();
                return ResultUtil.error(ResultEnum.UPLOAD_FAIL.getCode(), ResultEnum.UPLOAD_FAIL.getMsg());
            }
        }

        return ResultUtil.success(courseService.saveCourse(course).getId());
    }

    @CrossOrigin
    @PostMapping("/findCourseListByCondition")
    public Object findCourseListByCondition(CourseCondition courseCondition,
                                            @RequestParam(defaultValue = "0") Integer page,
                                            @RequestParam(defaultValue = "20") Integer size,
                                            @RequestParam(defaultValue = "createTime") String sortStr,
                                            @RequestParam(defaultValue = "desc") String asc) {
        Map<Integer, CourseCategory> courseCategoryMap = courseCategoryService.findAllCourseCategoryMap();

        Pageable pageable = HexUtil.getPageRequest(page, size, sortStr, asc);
        Page<Course> coursePage = courseService.findCourseListByCondition(courseCondition, pageable);
        List<CourseVO> courseVOList = Course2CourseVOConverter.converter(coursePage.getContent(), courseCategoryMap);
        return ResultUtil.success(new PageImpl<>(courseVOList, pageable, coursePage.getTotalElements()));
    }

    @PostMapping("/deleteCourse")
    public Object deleteCourse(Integer courseId) {
        Course course = courseService.findCourseById(courseId);
        if (null != course) {
            if (StringUtils.isNotBlank(course.getVideoImg()) && !course.getVideoImg().equals(DefaultImgNameEnum.VIDEO_IMG.getImgName())) {
                FileUtil.deleteFile(path, course.getVideoImg());
            }
            courseService.deleteCourseById(courseId);
            return ResultUtil.success();
        } else {
            return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), "课程id " + ResultEnum.ERROR_PARAM.getMsg());
        }
    }

    @CrossOrigin
    @PostMapping("/findCourseById")
    public Object findCourseById(Integer courseId) {
        Map<Integer, CourseCategory> courseCategoryMap = courseCategoryService.findAllCourseCategoryMap();

        Course course = courseService.findCourseById(courseId);
        if (null != course) {
            return ResultUtil.success(Course2CourseVOConverter.converter(course, courseCategoryMap));
        } else {
            return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), "视频id " + ResultEnum.ERROR_PARAM.getMsg());
        }
    }
}
