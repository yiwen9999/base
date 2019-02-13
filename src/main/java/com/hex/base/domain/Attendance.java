package com.hex.base.domain;

import com.hex.base.util.KeyUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 签到
 * <p>
 * User: hexuan
 * Date: 2018/7/5
 * Time: 下午5:14
 */
@Entity
@DynamicUpdate
@Data
public class Attendance implements Serializable {

    @Id
    private String id;

    /**
     * 会议id
     */
    private Integer meetingId;

    /**
     * 会议名称
     */
    private String meetingName;

    /**
     * 商品id
     */
    private Integer productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 地点id
     */
    private String placeId;

    /**
     * 地点名称
     */
    private String placeName;

    /**
     * 用户信息id
     */
    private String userInfoId;

    /**
     * 微信昵称
     */
    private String nickName;

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
     * 是否中奖
     */
    private Boolean winningRecord;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public Attendance() {
        this.id = KeyUtil.genUniqueKey();
    }
}
