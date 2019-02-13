package com.hex.base.domain;

import com.hex.base.util.KeyUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 * User: hexuan
 * Date: 2018/7/12
 * Time: 上午11:00
 */
@Entity
@DynamicUpdate
@Data
public class UserInfo implements Serializable {

    @Id
    private String id;

    /**
     * 微信id
     */
    private String openId;

    /**
     * 微信昵称
     */
    private String nickName;

    /**
     * 性别 true 男 false 女
     */
    private Boolean male;

    /**
     * 头像
     */
    private String icon;

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
    @Column(length = 64)
    private String hospital;

    /**
     * 地点id
     */
    @Column(length = 64)
    private String placeId;

    /**
     * 地点名称
     */
    @Column(length = 64)
    private String placeName;

    /**
     * 签到次数
     */
    private Integer attendanceTimes = new Integer(0);

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public UserInfo() {
        this.id = KeyUtil.genUniqueKey();
    }
}
