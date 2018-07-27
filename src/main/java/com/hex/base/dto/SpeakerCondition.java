package com.hex.base.dto;

import lombok.Data;

import java.util.List;

/**
 * User: hexuan
 * Date: 2018/7/12
 * Time: 下午4:10
 */
@Data
public class SpeakerCondition {
    private String name;

    private List<Integer> idList;
}