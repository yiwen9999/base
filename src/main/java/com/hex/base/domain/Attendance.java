package com.hex.base.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 签到
 *
 * User: hexuan
 * Date: 2018/7/5
 * Time: 下午5:14
 */
@Entity
@DynamicUpdate
@Getter
@Setter
public class Attendance implements Serializable{

    @Id
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 所在医院
     */
    private String hospital;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public Attendance() {
    }
}
