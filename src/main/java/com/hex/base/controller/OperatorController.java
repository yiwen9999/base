package com.hex.base.controller;

import com.hex.base.domain.Channel;
import com.hex.base.domain.Operator;
import com.hex.base.enums.ResultEnum;
import com.hex.base.service.ChannelService;
import com.hex.base.service.OperatorService;
import com.hex.base.service.RoleService;
import com.hex.base.util.HexUtil;
import com.hex.base.util.Md5SaltTool;
import com.hex.base.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作人员controller
 * <p>
 * User: hexuan
 * Date: 2017/8/11
 * Time: 下午4:07
 */
@RestController
public class OperatorController {

    @Autowired
    private OperatorService operatorService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ChannelService channelService;

    /**
     * 操作人员登录
     *
     * @param name     登录名
     * @param password 密码
     * @param request  request session存放登录操作人
     * @return
     */
    @PostMapping(value = "/login")
    public Object login(String name, String password, HttpServletRequest request) {
        Operator operator = operatorService.findOperatorByName(name);
        if (null == operator) {
            return ResultUtil.error(ResultEnum.ERROR_USERNAME.getCode(), ResultEnum.ERROR_USERNAME.getMsg());
        }
        boolean result = false;
        try {
            /**
             * 先判断操作帐号是否启用
             * 再判断密码是否正确
             */
            if (2 == operator.getState()) {
                result = Md5SaltTool.validPassword(password, operator.getPassword());
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return ResultUtil.error(ResultEnum.ERROR_PASSWORD.getCode(), ResultEnum.ERROR_PASSWORD.getMsg());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return ResultUtil.error(ResultEnum.ERROR_PASSWORD.getCode(), ResultEnum.ERROR_PASSWORD.getMsg());
        }
        if (result) {
            request.getSession().setAttribute("operator", operator);
            return ResultUtil.success(result);
        } else {
            return ResultUtil.error(ResultEnum.ERROR_PASSWORD.getCode(), ResultEnum.ERROR_PASSWORD.getMsg());
        }
    }

    /**
     * 登出
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/logout")
    public Object logout(HttpServletRequest request) {
        request.getSession().removeAttribute("operator");
        return ResultUtil.success();
    }

    /**
     * 获取操作人员信息，根据操作人员返回顶级频道集合
     *
     * @param request request获取操作人员
     * @return
     */
    @GetMapping(value = "/getOperatorInfo")
    public Object getOperatorInfo(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Operator creator = HexUtil.getOperator(request);
        List<Channel> topChannels = null;
        List<Channel> childChannels = null;
        if (creator != null) {
            Operator operator = creator;
            operator = operatorService.findOperatorById(operator.getId());
            map.put("operator", operator);
            /**
             * 根据操作人员身份获取可访问的顶级频道，身份需要为启用状态。
             * 若登录人为超级管理员，则返回所有顶级和子集频道。
             */
            if (null != operator.getRole() && operator.getRole().getState() == 2) {
                topChannels = operator.getRole().getUsingTopChannels();
                childChannels = operator.getRole().getUsingChildChannels();
            } else if (operator.getId().equals("root")) {
                topChannels = channelService.findChannelsByParentChannelIsNullOrderBySort();
                childChannels = channelService.findChannelsByParentChannelIsNotNullOrderBySort();
            }
            map.put("topChannels", topChannels);
            map.put("childChannels", childChannels);
            return ResultUtil.success(map);
        } else {
            return ResultUtil.error(ResultEnum.UN_LOGIN.getCode(), ResultEnum.UN_LOGIN.getMsg());
        }
    }

    /**
     * 操作人员保存
     *
     * @param operator 操作人员
     * @param roleId   角色id
     * @param request  request获取当前操作人员
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/saveOperator")
    public Object saveOperator(Operator operator, String roleId, HttpServletRequest request) throws Exception {
        if (StringUtils.isBlank(roleId)) {
            return ResultUtil.error(ResultEnum.ERROR_NULLPARAM.getCode(), "角色" + ResultEnum.ERROR_NULLPARAM.getMsg());
        }
        if (StringUtils.isBlank(operator.getName())) {
            return ResultUtil.error(ResultEnum.ERROR_NULLPARAM.getCode(), "登录名" + ResultEnum.ERROR_NULLPARAM.getMsg());
        }
        if (StringUtils.isBlank(operator.getPassword())) {
            return ResultUtil.error(ResultEnum.ERROR_NULLPARAM.getCode(), "密码" + ResultEnum.ERROR_NULLPARAM.getMsg());
        }
        if (!operatorService.validateOperator(operator.getName(), null, null, null)) {
            return ResultUtil.error(ResultEnum.ERROR_EXIST.getCode(), "登录名" + ResultEnum.ERROR_EXIST.getMsg());
        }
        if (StringUtils.isNotBlank(operator.getMobile())) {
            if (!operatorService.validateOperator(null, null, operator.getMobile(), null)) {
                return ResultUtil.error(ResultEnum.ERROR_EXIST.getCode(), "手机号" + ResultEnum.ERROR_EXIST.getMsg());
            }
        }
        if (StringUtils.isNotBlank(operator.getEmail())) {
            if (!operatorService.validateOperator(null, operator.getEmail(), null, null)) {
                return ResultUtil.error(ResultEnum.ERROR_EXIST.getCode(), "邮箱" + ResultEnum.ERROR_EXIST.getMsg());
            }
        }

        Operator creator = HexUtil.getOperator(request);
        operator.setName(operator.getName());
        operator.setNickname(operator.getNickname());
        operator.setEmail(operator.getEmail());
        operator.setMobile(operator.getMobile());
        operator.setRole(roleService.findRoleById(roleId));
        operator.setPassword(Md5SaltTool.getEncryptedPwd(operator.getPassword()));
        operator.setCreator(creator);
        return ResultUtil.success(operatorService.saveOperator(operator));
    }

    /**
     * 操作人员修改
     *
     * @param operator 操作人员
     * @param roleId   角色id
     * @param request  request获取当前操作人员
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/updateOperator")
    public Object updateOperator(Operator operator, String roleId, HttpServletRequest request) throws Exception {
        if (StringUtils.isBlank(roleId)) {
            return ResultUtil.error(ResultEnum.ERROR_NULLPARAM.getCode(), "角色" + ResultEnum.ERROR_NULLPARAM.getMsg());
        }
        if (StringUtils.isBlank(operator.getName())) {
            return ResultUtil.error(ResultEnum.ERROR_NULLPARAM.getCode(), "登录名" + ResultEnum.ERROR_NULLPARAM.getMsg());
        }

        Operator creator = HexUtil.getOperator(request);
        Operator saveOperator = operatorService.findOperatorById(operator.getId());
        if (null == saveOperator) {
            return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), ResultEnum.ERROR_PARAM.getMsg());
        }
        if (!operatorService.validateOperator(operator.getName(), null, null, operator.getId())) {
            return ResultUtil.error(ResultEnum.ERROR_EXIST.getCode(), "登录名" + ResultEnum.ERROR_EXIST.getMsg());
        }
        if (StringUtils.isNotBlank(operator.getMobile())) {
            if (!operatorService.validateOperator(null, null, operator.getMobile(), operator.getId())) {
                return ResultUtil.error(ResultEnum.ERROR_EXIST.getCode(), "手机号" + ResultEnum.ERROR_EXIST.getMsg());
            }
        }
        if (StringUtils.isNotBlank(operator.getEmail())) {
            if (!operatorService.validateOperator(null, operator.getEmail(), null, operator.getId())) {
                return ResultUtil.error(ResultEnum.ERROR_EXIST.getCode(), "邮箱" + ResultEnum.ERROR_EXIST.getMsg());
            }
        }
        saveOperator.setName(operator.getName());
        saveOperator.setNickname(operator.getNickname());
        saveOperator.setEmail(operator.getEmail());
        saveOperator.setMobile(operator.getMobile());
        saveOperator.setRole(roleService.findRoleById(roleId));
        saveOperator.setCreator(creator);
        return ResultUtil.success(operatorService.saveOperator(saveOperator));
    }

    /**
     * 根据创建时间，登录名查询操作人员列表
     *
     * @param beginTime 起始创建时间
     * @param endTime   结束创建时间
     * @param name      登录名
     * @return
     */
    @PostMapping(value = "/searchOperatorList")
    public Object searchOperatorList(Integer page, Integer size, String sortStr, String asc, String beginTime, String endTime, String name) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("beginTime", HexUtil.formatBeginTimeString(beginTime));
        condition.put("endTime", HexUtil.formatEndTimeString(endTime));
        condition.put("name", name);
        return ResultUtil.success(operatorService.findOperators(condition, HexUtil.getPageRequest(page, size, sortStr, asc)));
    }

    /**
     * 停启用操作人员
     *
     * @param operatorId
     * @return
     */
    @PostMapping(value = "/updateOperatorState")
    public Object updateOperatorState(String operatorId) {
        if (null == operatorId || operatorId.equals("")) {
            return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), ResultEnum.ERROR_PARAM.getMsg());
        }
        Operator operator = operatorService.findOperatorById(operatorId);
        if (null == operator) {
            return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), ResultEnum.ERROR_PARAM.getMsg());
        }
        if (operator.getState() == 2) {
            operator.setState(new Integer(-1));
        } else {
            operator.setState(new Integer(2));
        }
        return ResultUtil.success(operatorService.saveOperator(operator));
    }

    /**
     * 删除操作人员
     *
     * @param operator 操作人员
     * @return
     */
    @PostMapping(value = "/deleteOperator")
    public Object deleteOperator(Operator operator) {
        try {
            operatorService.deleteOperator(operator);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(ResultEnum.ERROR_DELETE.getCode(), ResultEnum.ERROR_DELETE.getMsg());
        }
        return ResultUtil.success();
    }

    /**
     * 修改用户密码
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param request     request获取当前用户
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @PostMapping(value = "/updatePassword")
    public Object updatePassword(String oldPassword, String newPassword, HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Operator operator = HexUtil.getOperator(request);
        if (null == operator) {
            return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), ResultEnum.ERROR_PARAM.getMsg());
        }
        if (Md5SaltTool.validPassword(oldPassword, operator.getPassword())) {
            operator.setPassword(Md5SaltTool.getEncryptedPwd(newPassword));
            return ResultUtil.success(operatorService.saveOperator(operator));
        } else {
            return ResultUtil.error(ResultEnum.ERROR_PASSWORD.getCode(), ResultEnum.ERROR_PASSWORD.getMsg());
        }
    }

    /**
     * 重置密码
     *
     * @param id 账号id
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @PostMapping(value = "/resetPassword")
    public Object resetPassword(String id) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (null == id || "".equals(id)) {
            return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), ResultEnum.ERROR_PARAM.getMsg());
        }
        Operator operator = operatorService.findOperatorById(id);
        if (null == operator) {
            return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), ResultEnum.ERROR_PARAM.getMsg());
        }
        operator.setPassword(Md5SaltTool.getEncryptedPwd("000000"));
        return ResultUtil.success(operatorService.saveOperator(operator));
    }

    /**
     * 校验注册信息是否可用
     *
     * @param name   用户名
     * @param email  邮箱
     * @param mobile 手机号
     * @return
     */
    @PostMapping(value = "/validateOperator")
    public Object validateOperator(String name, String email, String mobile, String id) {
        return ResultUtil.success(operatorService.validateOperator(name, email, mobile, id));
    }

    /**
     * 管理员切换操作人员
     *
     * @param request
     * @param name
     * @return
     */
    @GetMapping(value = "/adminChangeOperator")
    public Object adminChangeOperator(HttpServletRequest request, String name) {
        Operator operator = HexUtil.getOperator(request);
        if (null != operator && operator.getId().equals("root")) {
            operator = operatorService.findOperatorByName(name);
            request.getSession().setAttribute("operator", operator);
        }
        return ResultUtil.success(operator);
    }
}
