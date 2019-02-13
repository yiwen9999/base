package com.hex.base.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * User: hexuan
 * Date: 2018/10/16
 * Time: 2:58 PM
 */
@Data
public class AttendanceForm {

    /**
     * 会议id
     */
    @NotNull(message = "会议id不能为空")
    private Integer meetingId;

    /**
     * 会议名称
     */
    @NotBlank(message = "会议名称不能为空")
    private String meetingName;

    /**
     * 商品id
     */
    @NotNull(message = "商品id不能为空")
    private Integer productId;

    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空")
    private String productName;

    /**
     * 地点id
     */
    @NotNull(message = "地点id不能为空")
    private String placeId;

    /**
     * 地点名称
     */
    @NotBlank(message = "地点名称不能为空")
    private String placeName;

    /**
     * 用户信息id
     */
//    @NotBlank(message = "用户id不能为空")
    private String userInfoId;

    /**
     * 微信昵称
     */
//    @NotBlank(message = "昵称不能为空")
    private String nickName;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String name;

    /**
     * 联系电话
     */
    @NotBlank(message = "联系电话不能为空")
    private String phone;

    /**
     * 所在医院
     */
    @NotBlank(message = "所在医院不能为空")
    private String hospital;

}
