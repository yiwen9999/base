package com.hex.base.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 地点
 * User: hexuan
 * Date: 2018/7/12
 * Time: 上午11:10
 */
@Entity
@DynamicUpdate
@Data
public class Place implements Serializable {

    @Id
    private String id;

    /**
     * 父id
     */
    private String parentId;

    /**
     * 名称
     */
    private String name;

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
