package com.hex.base.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 会议
 * <p>
 * User: hexuan
 * Date: 2018/7/5
 * Time: 下午4:43
 */
@Entity
@DynamicUpdate
@Data
public class Meeting implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 会议名称
     */
    private String name;

    /**
     * 地址信息
     */
    private String address;

    /**
     * 日期信息
     */
    private String timeStr;

    /**
     * 会议小贴士
     */
    private String tips;

    /**
     * 会议图片
     */
    private String img = "default_meeting_img.jpg";

    /**
     * 会议logo
     */
    private String logo = "default_meeting_logo.jpg";

    /**
     * 状态
     */
    private Integer state;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
