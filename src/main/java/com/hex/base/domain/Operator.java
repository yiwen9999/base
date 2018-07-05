package com.hex.base.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 操作人员
 *
 * @author hexuan
 */
@Entity
@DynamicUpdate
@Getter
@Setter
public class Operator implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    @Column(length = 50)
    private String id;

    // 用户名
    @NotEmpty(message = "账号不能为空")
//    @Size(min = 2, max = 20, message = "用户名长度在2-20字之间")
    @Column(nullable = false, length = 40, unique = true)
    private String name;

    // 昵称
    @NotEmpty(message = "姓名不能为空")
//    @Size(min = 2, max = 20, message = "姓名长度在2-20字之间")
    @Column(nullable = false, length = 40)
    private String nickname;

    // 密码
    @NotEmpty(message = "密码不能为空")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String password;

    /**
     * 手机、昵称，仅限内部人员使用（没有engineer和customer的）
     * 含有对应身份的，去对应实体内取值
     */

    // 邮箱
//    @NotEmpty(message = "邮箱不能为空")
    @Size(max = 50)
//    @Email(message = "邮箱格式不对")
    @Column(length = 50)
    private String email;

    // 手机
//    @NotEmpty(message = "手机不能为空")
//    @Size(max = 11, min = 11, message = "手机号长度需为11位")
    @Column(length = 11)
    private String mobile;

    // 状态【2：启用】【-1：停用】
    private Integer state = new Integer(2);

    // 角色
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    // 创建时间
    private Date createTime = new Date();

    // 创建人
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Operator creator;

    public Operator() {
    }

    @Override
    public String toString() {
        return "Operator{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", state=" + state +
                ", role=" + role +
                '}';
    }
}
