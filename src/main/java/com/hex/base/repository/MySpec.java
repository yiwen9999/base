package com.hex.base.repository;

import com.hex.base.domain.Course;
import com.hex.base.domain.CourseCategory;
import com.hex.base.domain.Operator;
import com.hex.base.domain.Role;
import com.hex.base.dto.CourseCategoryCondition;
import com.hex.base.dto.CourseCondition;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 复杂查询相关
 * <p>
 * User: hexuan
 * Date: 2017/9/1
 * Time: 上午10:32
 */
public class MySpec {

    public static Specification<Role> findRolesByCreateTimeAndName(Date beginTime, Date endTime, String name) {
        return new Specification<Role>() {
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicate = new ArrayList<>();
                if (!StringUtils.isEmpty(name)) {
                    predicate.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + name + "%"));
                }
                if (null != beginTime) {
                    predicate.add(criteriaBuilder.greaterThan(root.get("createTime").as(Date.class), beginTime));
                }
                if (null != endTime) {
                    predicate.add(criteriaBuilder.lessThan(root.get("createTime").as(Date.class), endTime));
                }
                Predicate[] pre = new Predicate[predicate.size()];
                return criteriaQuery.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    public static Specification<Operator> findOperators(Map<String, Object> condition) {
        return new Specification<Operator>() {
            @Override
            public Predicate toPredicate(Root<Operator> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Date beginTime = (Date) condition.get("beginTime");
                Date endTime = (Date) condition.get("endTime");
                String name = (String) condition.get("name");

                List<Predicate> predicate = new ArrayList<>();
                if (!StringUtils.isEmpty(name)) {
                    predicate.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + name + "%"));
                }
                if (null != beginTime) {
                    predicate.add(criteriaBuilder.greaterThan(root.get("createTime").as(Date.class), beginTime));
                }
                if (null != endTime) {
                    predicate.add(criteriaBuilder.lessThan(root.get("createTime").as(Date.class), endTime));
                }
                Predicate[] pre = new Predicate[predicate.size()];
                return criteriaQuery.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    public static Specification<CourseCategory> findCourseCategorys(CourseCategoryCondition courseCategoryCondition) {
        return new Specification<CourseCategory>() {
            @Override
            public Predicate toPredicate(Root<CourseCategory> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicate = new ArrayList<>();

                if (!CollectionUtils.isEmpty(courseCategoryCondition.getStateList())) {
                    List<Predicate> pList = new ArrayList<>();
                    for (Integer state : courseCategoryCondition.getStateList()) {
                        pList.add(criteriaBuilder.equal(root.get("state").as(Integer.class), state));
                    }
                    predicate.add(criteriaBuilder.or(pList.toArray(new Predicate[pList.size()])));
                }

                Predicate[] pre = new Predicate[predicate.size()];
                return criteriaQuery.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    public static Specification<Course> findCourses(CourseCondition courseCondition) {
        return new Specification<Course>() {
            @Override
            public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicate = new ArrayList<>();

                if (!CollectionUtils.isEmpty(courseCondition.getCourseCategoryIdList())) {
                    List<Predicate> pList = new ArrayList<>();
                    for (Integer courseCategoryId : courseCondition.getCourseCategoryIdList()) {
                        pList.add(criteriaBuilder.equal(root.get("courseCategoryId").as(Integer.class), courseCategoryId));
                    }
                    predicate.add(criteriaBuilder.or(pList.toArray(new Predicate[pList.size()])));
                }

                Predicate[] pre = new Predicate[predicate.size()];
                return criteriaQuery.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

}
