package com.hex.base.domain;

import com.hex.base.enums.ObjectStateEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品
 * <p>
 * User: hexuan
 * Date: 2018/7/9
 * Time: 上午10:06
 */
@Entity
@DynamicUpdate
@Data
public class Product implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 图片
     */
    private String icon = "default_product_icon.jpg";

    /**
     * 状态 1为启用 -1为停用
     */
    private Integer state = ObjectStateEnum.START_USING.getCode();

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
