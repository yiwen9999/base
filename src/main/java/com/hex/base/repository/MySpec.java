package com.hex.base.repository;

import com.hex.base.domain.*;
import com.hex.base.dto.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

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

    public static Specification<Speaker> findSpeakers(SpeakerCondition speakerCondition) {
        return new Specification<Speaker>() {
            @Override
            public Predicate toPredicate(Root<Speaker> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicate = new ArrayList<>();

                if (StringUtils.isNotBlank(speakerCondition.getName())) {
                    predicate.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + speakerCondition.getName() + "%"));
                }

                if (!CollectionUtils.isEmpty(speakerCondition.getIdList())) {
                    CriteriaBuilder.In<Integer> idIn = criteriaBuilder.in(root.get("id").as(Integer.class));
                    for (Integer id : speakerCondition.getIdList()) {
                        idIn.value(id);
                    }
                    predicate.add(idIn);
                }

                Predicate[] pre = new Predicate[predicate.size()];
                return criteriaQuery.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    public static Specification<Meeting> findMeetings(MeetingCondition meetingCondition) {
        return new Specification<Meeting>() {
            @Override
            public Predicate toPredicate(Root<Meeting> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicate = new ArrayList<>();

                if (StringUtils.isNotBlank(meetingCondition.getName())) {
                    predicate.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + meetingCondition.getName() + "%"));
                }

                if (null != meetingCondition.getState()) {
                    predicate.add(criteriaBuilder.equal(root.get("state").as(Integer.class), meetingCondition.getState()));
                }

                Predicate[] pre = new Predicate[predicate.size()];
                return criteriaQuery.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    public static Specification<Product> findProducts(ProductCondition productCondition) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicate = new ArrayList<>();

                if (StringUtils.isNotBlank(productCondition.getName())) {
                    predicate.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + productCondition.getName() + "%"));
                }

                if (null != productCondition.getState()) {
                    predicate.add(criteriaBuilder.equal(root.get("state").as(Integer.class), productCondition.getState()));
                }

                Predicate[] pre = new Predicate[predicate.size()];
                return criteriaQuery.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    public static Specification<Schedule> findSchedules(ScheduleCondition scheduleCondition) {
        return new Specification<Schedule>() {
            @Override
            public Predicate toPredicate(Root<Schedule> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicate = new ArrayList<>();

                if (null != scheduleCondition.getMeetingId()) {
                    predicate.add(criteriaBuilder.equal(root.get("meetingId").as(Integer.class), scheduleCondition.getMeetingId()));
                }

                if (null != scheduleCondition.getTime()) {
                    predicate.add(criteriaBuilder.equal(root.get("time").as(Date.class), scheduleCondition.getTime()));
                }

                Predicate[] pre = new Predicate[predicate.size()];
                return criteriaQuery.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

}
