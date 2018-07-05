package com.hex.base.service;

import com.hex.base.domain.Operator;
import com.hex.base.domain.Role;
import com.hex.base.repository.MySpec;
import com.hex.base.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * User: hexuan
 * Date: 2017/8/14
 * Time: 下午1:12
 */
@Service
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    private OperatorRepository operatorRepository;

    @Override
    public Operator saveOperator(Operator operator) {
        return operatorRepository.save(operator);
    }

    @Override
    public Operator findOperatorById(String id) {
        return operatorRepository.findOne(id);
    }

    @Override
    public List<Operator> findAllOperatorList() {
        return operatorRepository.findAll();
    }

    public Operator findOperatorByName(String name) {
        return operatorRepository.findByName(name);
    }

    @Override
    public void deleteOperator(Operator operator) {
        operatorRepository.delete(operator);
    }

    @Override
    public Long countOperatorsByRole(Role role) {
        return operatorRepository.countOperatorsByRole(role);
    }

    @Override
    public Page<Operator> findOperators(Map<String, Object> condition, PageRequest pageRequest) {
        return operatorRepository.findAll(MySpec.findOperators(condition), pageRequest);
    }

    @Override
    public Operator findOperatorByEmail(String email) {
        return operatorRepository.findByEmail(email);
    }

    @Override
    public Operator findOperatorByMobile(String mobile) {
        return operatorRepository.findByMobile(mobile);
    }

    @Override
    public Boolean validateOperator(String name, String email, String mobile, String id) {
        boolean flag = true;
        Operator operator = null;
        if (null != name && !"".equals(name)) {
            operator = operatorRepository.findByName(name);
        }
        if (null != email && !"".equals(email)) {
            operator = operatorRepository.findByEmail(email);
        }
        if (null != mobile && !"".equals(mobile)) {
            operator = operatorRepository.findByMobile(mobile);
        }
        return validateFunction(flag, operator, id);
    }

    private Boolean validateFunction(Boolean flag, Operator operator, String id) {
        if (null != operator) { // 是否根据信息在数据库中找到登录账号
            if (null != id && !"".equals(id)) { // 修改id是否存在
                if (!operator.getId().equals(id)) { // 修改id和库中找到不是同一账号时，返回false
                    flag = false;
                }
            } else {// 修改id不存在时，直接返回false
                flag = false;
            }
        }
        return flag;
    }
}
