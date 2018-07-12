package com.hex.base.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * User: hexuan
 * Date: 2018/7/9
 * Time: 上午10:10
 */
@Entity
@DynamicUpdate
@Data
public class SatelliteMeeting implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 会议id
     */
    private Integer meetingId;

    /**
     * 图片
     */
    private String img = "default_satelliteMeeting_img.jpg";

    /**
     * 会议信息
     */
    private String info;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
