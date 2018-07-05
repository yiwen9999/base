package com.hex.base.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 频道
 *
 * @author hexuan
 */
@Entity
@DynamicUpdate
@Getter
@Setter
public class Channel implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    @Column(length = 50)
    private String id;

    // 频道名称
    @Column(length = 50)
    private String name;

    // 频道路径
    @Column(length = 50)
    private String url;

    // 状态【2：启用】【-1：停用】
    private Integer state = new Integer(2);

    // 排序
    private Integer sort;

    // 父频道
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Channel parentChannel;

    // 所有子频道集合
    @OneToMany(mappedBy = "parentChannel", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @OrderBy(value = "sort,create_time")
    @JsonIgnore
    private List<Channel> childChannels = new ArrayList<>();

    // 在用子频道集合
    @OneToMany(mappedBy = "parentChannel")
    @OrderBy(value = "sort,create_time")
    @JsonIgnore
    private List<Channel> usingChannels = new ArrayList<>();

    // 创建时间
    private Date createTime = new Date();

    // 创建人
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Operator creator;

    public Channel() {
    }
}
