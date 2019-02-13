package com.hex.base.dto;

import lombok.Data;

/**
 * User: hexuan
 * Date: 2018/10/17
 * Time: 3:54 PM
 */
@Data
public class AttendanceCondition {
    /**
     * 会议id
     */
    private Integer meetingId;

    /**
     * 商品id
     */
    private Integer productId;
}
