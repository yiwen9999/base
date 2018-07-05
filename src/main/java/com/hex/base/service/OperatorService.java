package com.hex.base.service;

import com.hex.base.domain.Operator;
import com.hex.base.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

public interface OperatorService {
    Operator saveOperator(Operator operator);

    Operator findOperatorById(String id);

    void deleteOperator(Operator operator);

    List<Operator> findAllOperatorList();

    Operator findOperatorByName(String name);

    Operator findOperatorByEmail(String email);

    Operator findOperatorByMobile(String mobile);

    /**
     * 根据角色统计操作人员数量
     *
     * @param role 角色
     * @return
     */
    Long countOperatorsByRole(Role role);

    Page<Operator> findOperators(Map<String, Object> condition, PageRequest pageRequest);

    /**
     * 校验注册账号信息
     *
     * @param name   登录名
     * @param email  邮箱
     * @param mobile 手机号
     * @return
     */
    Boolean validateOperator(String name, String email, String mobile, String id);
}
