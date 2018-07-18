package com.hex.base.vo;

import lombok.Data;

/**
 * User: hexuan
 * Date: 2018/7/17
 * Time: 下午2:19
 */
@Data
public class SatelliteMeetingVO {

    private Integer id;

    /**
     * 会议id
     */
    private Integer meetingId;

    /**
     * 图片
     */
    private String img;

    /**
     * 会议信息
     */
    private String info;
}
