package com.hex.base.service;


import com.hex.base.domain.Role;
import com.hex.base.repository.MySpec;
import com.hex.base.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * User: hexuan
 * Date: 2017/8/14
 * Time: 下午1:33
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public void deleteRoleList(String[] ids) {
        List<Role> roles = roleRepository.findRolesByIdInOrderByCreateTimeAsc(ids);
        roleRepository.deleteInBatch(roles);
    }

    @Override
    public Role findRoleById(String id) {
        return roleRepository.findOne(id);
    }

    @Override
    public List<Role> findAllRoleList() {
        return roleRepository.findAll();
    }

    @Override
    public List findRoleListByCreateTimeAndName(Date beginTime, Date endTime, String name) {
        Sort sort = new Sort(Sort.Direction.ASC, "createTime");
        return roleRepository.findAll(MySpec.findRolesByCreateTimeAndName(beginTime, endTime, name), sort);
    }

    @Override
    public Role invalidRole(Role role) {
        Role role1 = roleRepository.findOne(role.getId());
        role1.setState(new Integer(-2));
        roleRepository.save(role1);
        return role1;
    }

    @Override
    public List<Role> invalidRoleList(String[] ids) {
        List<Role> roles = roleRepository.findRolesByIdInOrderByCreateTimeAsc(ids);
        for (int i = 0; i < roles.size(); i++) {
            roles.get(i).setState(new Integer(-2));
        }
        return roleRepository.save(roles);
    }

    @Override
    public List<Role> findRolesByStateOrderByCreateTimeDesc(Integer state) {
        return roleRepository.findRolesByStateOrderByCreateTimeDesc(state);
    }

    @Override
    public Role findFirstRoleByNameEquals(String name) {
        return roleRepository.findFirstByNameEquals(name);
    }
}
