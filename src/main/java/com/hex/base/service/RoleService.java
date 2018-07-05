package com.hex.base.service;

import com.hex.base.domain.Role;

import java.util.Date;
import java.util.List;

public interface RoleService {
    Role saveRole(Role role);

    void deleteRole(Role role);

    void deleteRoleList(String[] ids);

    Role findRoleById(String id);

    List<Role> findAllRoleList();

    List findRoleListByCreateTimeAndName(Date beginTime, Date endTime, String name);

    // 作废角色
    Role invalidRole(Role role);

    // 批量作废角色
    List<Role> invalidRoleList(String[] ids);

    // 根据状态查询角色集合
    List<Role> findRolesByStateOrderByCreateTimeDesc(Integer state);

    /**
     * 根据名称获取第一个匹配的角色
     *
     * @param name
     * @return
     */
    Role findFirstRoleByNameEquals(String name);
}
