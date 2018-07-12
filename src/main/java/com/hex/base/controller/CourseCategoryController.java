package com.hex.base.controller;

import com.hex.base.converter.CourseCategory2CourseCategoryVOConverter;
import com.hex.base.domain.CourseCategory;
import com.hex.base.enums.ResultEnum;
import com.hex.base.exception.MyException;
import com.hex.base.form.CourseCategoryForm;
import com.hex.base.service.CourseCategoryService;
import com.hex.base.util.HexUtil;
import com.hex.base.util.ResultUtil;
import com.hex.base.vo.CourseCategoryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 课程分类controller
 * <p>
 * User: hexuan
 * Date: 2018/7/11
 * Time: 上午10:12
 */
@RestController
public class CourseCategoryController {

    @Autowired
    private CourseCategoryService courseCategoryService;

    /**
     * 保存课程分类
     *
     * @param courseCategoryForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/saveCourseCategory")
    public Object saveCourseCategory(@Valid CourseCategoryForm courseCategoryForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new MyException(ResultEnum.ERROR_PARAM.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        CourseCategory courseCategory = new CourseCategory();
        if (null != courseCategoryForm.getId()) {
            courseCategory = courseCategoryService.findCourseCategoryById(courseCategoryForm.getId());
        }
        BeanUtils.copyProperties(courseCategoryForm, courseCategory);
        return ResultUtil.success(courseCategoryService.saveCourseCategory(courseCategory).getId());
    }

    /**
     * 查询所有课程分类集合
     *
     * @param page
     * @param size
     * @param sortStr
     * @param asc
     * @return
     */
    @GetMapping("/findAllCourseCategoryList")
    public Object findAllCourseCategoryList(@RequestParam(defaultValue = "0") Integer page,
                                            @RequestParam(defaultValue = "50") Integer size,
                                            @RequestParam(defaultValue = "sort") String sortStr,
                                            @RequestParam(defaultValue = "asc") String asc) {
        Pageable pageable = HexUtil.getPageRequest(page, size, sortStr, asc);
        Page<CourseCategory> courseCategoryPage = courseCategoryService.findAllCourseCategoryList(pageable);
        List<CourseCategoryVO> courseCategoryVOList = CourseCategory2CourseCategoryVOConverter.converter(courseCategoryPage.getContent());
        return ResultUtil.success(new PageImpl<>(courseCategoryVOList, pageable, courseCategoryPage.getTotalElements()));
    }

    /**
     * 查询启用课程分类集合
     *
     * @param page
     * @param size
     * @param sortStr
     * @param asc
     * @return
     */
    @GetMapping("/findUsingCourseCategoryList")
    public Object findUsingCourseCategoryList(@RequestParam(defaultValue = "0") Integer page,
                                              @RequestParam(defaultValue = "50") Integer size,
                                              @RequestParam(defaultValue = "sort") String sortStr,
                                              @RequestParam(defaultValue = "asc") String asc) {
        Page<CourseCategory> courseCategoryPage = courseCategoryService.findUsingCourseCategoryList(HexUtil.getPageRequest(page, size, sortStr, asc));
        List<CourseCategoryVO> courseCategoryVOList = CourseCategory2CourseCategoryVOConverter.converter(courseCategoryPage.getContent());
        return ResultUtil.success(new PageImpl<>(courseCategoryVOList, HexUtil.getPageRequest(page, size, sortStr, asc), courseCategoryPage.getTotalElements()));
    }

    /**
     * 停用&启用课程分类
     *
     * @param courseCategoryId
     * @return
     */
    @PostMapping("/updateCourseCategoryState")
    public Object updateCourseCategoryState(Integer courseCategoryId) {
        CourseCategory courseCategory = courseCategoryService.updateCourseCategoryStateById(courseCategoryId);
        if (null != courseCategory) {
            return ResultUtil.success(CourseCategory2CourseCategoryVOConverter.converter(courseCategory));
        } else {
            return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), ResultEnum.ERROR_PARAM.getMsg());
        }
    }
}
