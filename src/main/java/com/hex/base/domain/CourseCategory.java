package com.hex.base.domain;

import com.hex.base.enums.ObjectStateEnum;
import com.hex.base.util.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 课程分类
 * User: hexuan
 * Date: 2018/7/11
 * Time: 上午9:59
 */
@Entity
@DynamicUpdate
@Data
public class CourseCategory implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类状态 1为启用 -1为停用
     */
    private Integer state = ObjectStateEnum.START_USING.getCode();

    /**
     * 分类排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public ObjectStateEnum stateStr() {
        return EnumUtil.getCode(state, ObjectStateEnum.class);
    }
}
