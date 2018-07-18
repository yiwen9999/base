package com.hex.base.vo;

import lombok.Data;

/**
 * User: hexuan
 * Date: 2018/7/13
 * Time: 下午5:16
 */
@Data
public class ProductVO {

    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 图片
     */
    private String icon;

    /**
     * 状态值
     */
    private Integer state;

    /**
     * 状态
     */
    private String stateStr;
}
