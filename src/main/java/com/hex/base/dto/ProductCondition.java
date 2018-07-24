package com.hex.base.dto;

import lombok.Data;

/**
 * User: hexuan
 * Date: 2018/7/18
 * Time: 下午1:39
 */
@Data
public class ProductCondition {

    /**
     * 名称
     */
    private String name;

    /**
     * 状态 1为启用 -1为停用
     */
    private Integer state;
}
