package com.hex.base.domain;

import com.hex.base.enums.DefaultImgNameEnum;
import com.hex.base.enums.MeetingStateEnum;
import com.hex.base.util.EnumUtil;
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
    private String img = DefaultImgNameEnum.MEETING_IMG.getImgName();

    /**
     * 会议logo
     */
    private String logo = DefaultImgNameEnum.MEETING_LOGO.getImgName();

    /**
     * 状态
     */
    private Integer state = MeetingStateEnum.NOT_START.getCode();

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public MeetingStateEnum stateStr() {
        return EnumUtil.getCode(state, MeetingStateEnum.class);
    }
}
