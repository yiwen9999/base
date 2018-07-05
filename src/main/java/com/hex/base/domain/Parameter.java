package com.hex.base.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 参数库 【暂未用到】
 * <p>
 * User: hexuan
 * Date: 2017/8/25
 * Time: 下午5:34
 */
@Entity
@DynamicUpdate
@Getter
@Setter
public class Parameter implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    @Column(length = 50)
    private String id;

    // 名称
    private String name;

    // 参数code
    private String code;

    // 排序
    private Integer sort;

    // 状态 【2：启用】【-1：停用】【-2:作废】
    private Integer state = new Integer(2);

    // 父参数
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parameter parent;

    // 子参数集合
    @OneToMany(mappedBy = "parent", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @OrderBy(value = "sort")
    @JsonIgnore
    private List<Parameter> childs = new ArrayList<>();

    // 在用子参数集合
    @OneToMany(mappedBy = "parent")
    @OrderBy(value = "sort")
    @Where(clause = "state=2")
    @JsonIgnore
    private List<Parameter> usingChilds = new ArrayList<>();

    // 创建时间
    private Date createTime = new Date();

    // 创建人
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Operator creator;

    public Parameter() {
    }
}
