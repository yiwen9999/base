package com.hex.base.controller;

import com.hex.base.domain.Role;
import com.hex.base.enums.ResultEnum;
import com.hex.base.service.ChannelService;
import com.hex.base.service.OperatorService;
import com.hex.base.service.RoleService;
import com.hex.base.util.HexUtil;
import com.hex.base.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 角色controller
 * <p>
 * User: hexuan
 * Date: 2017/8/20
 * Time: 下午12:15
 */
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private OperatorService operatorService;

    /**
     * 根据创建时间，名称查询角色列表
     *
     * @param beginTime 起始创建时间
     * @param endTime   结束创建时间
     * @param name      角色名称
     * @return
     */
    @PostMapping(value = "/searchRoleList")
    public Object searchRoleList(String beginTime, String endTime, String name) {
        return ResultUtil.success(roleService.findRoleListByCreateTimeAndName(HexUtil.formatBeginTimeString(beginTime), HexUtil.formatEndTimeString(endTime), name));
    }

    /**
     * 获取全部角色列表
     *
     * @return
     */
    @GetMapping(value = "/getRoleList")
    public Object getRoleList() {
        return ResultUtil.success(roleService.findAllRoleList());
    }

    /**
     * 添加or修改角色
     *
     * @param name       角色名称
     * @param remark     角色说明
     * @param channelIds 频道id集合
     * @param id         角色id
     * @return
     */
    @PostMapping(value = "/saveRole")
    public Object saveRole(String name, String remark, @RequestParam String[] channelIds, String id) {
        Role role = null;
        if (null != id && !id.equals("")) {
            role = roleService.findRoleById(id);
        }
        if (role == null) {
            role = new Role();
        }
        role.setName(name);
        role.setRemark(remark);
        if (null != channelIds && channelIds.length > 0) {
            role.getChannels().clear();
            role.getChannels().addAll(channelService.findChannelsByIdInOrderByCreateTimeAsc(channelIds));
        }
        return ResultUtil.success(roleService.saveRole(role));
    }

    /**
     * 删除角色
     *
     * @param role 角色
     * @return
     */
    @PostMapping(value = "/deleteRole")
    public Object deleteRole(Role role) {
        try {
            if (operatorService.countOperatorsByRole(role) > 0) {
                return ResultUtil.error(ResultEnum.ERROR_DELETE.getCode(), ResultEnum.ERROR_DELETE.getMsg());
            }
            roleService.deleteRole(role);
        } catch (Exception e) {
            return ResultUtil.error(ResultEnum.ERROR_DELETE.getCode(), ResultEnum.ERROR_DELETE.getMsg());
        }
        return ResultUtil.success();
        // return ResultUtil.success(roleService.invalidRole(role));
    }

    // 删除作废角色
    @PostMapping(value = "/deleteRoleList")
    public Object deleteRoleList(@RequestParam String[] ids) {
        try {
            roleService.deleteRoleList(ids);
        } catch (Exception e) {
            return ResultUtil.error(ResultEnum.ERROR_DELETE.getCode(), ResultEnum.ERROR_DELETE.getMsg());
        }
        return ResultUtil.success();
        // return ResultUtil.success(roleService.invalidRoleList(ids));
    }

    /**
     * 根据id获取角色信息
     *
     * @param id 角色id
     * @return
     */
    @PostMapping(value = "/getRoleInfo")
    public Object getRoleInfo(String id) {
        return ResultUtil.success(roleService.findRoleById(id));
    }

    /**
     * 根据角色id停用or启用角色
     *
     * @param id 角色id
     * @return
     */
    @PostMapping(value = "/updateRoleState")
    public Object updateRoleState(String id) {
        Role role = roleService.findRoleById(id);
        if (null != role && null != role.getState()) {
            if (role.getState() == 2) {
                role.setState(new Integer(-1));
            } else if (role.getState() == -1) {
                role.setState(new Integer(2));
            }
        }
        return ResultUtil.success(roleService.saveRole(role));
    }

    /**
     * 获取在用的角色集合
     *
     * @return
     */
    @GetMapping(value = "/getUsingRoleList")
    public Object getUsingRoleList() {
        return ResultUtil.success(roleService.findRolesByStateOrderByCreateTimeDesc(new Integer(2)));
    }

    /**
     * 根据角色id获取角色所选频道
     *
     * @param roleId 角色id
     * @return
     */
    @PostMapping(value = "/getRoleChannelList")
    public Object getRoleChannelList(String roleId) {
        Role role = roleService.findRoleById(roleId);
        Map<String, Object> map = new HashMap<>();
        if (null != role) {
            map.put("topChannelList", role.getTopChannels());
            map.put("childChannelList", role.getChildChannels());
        }
        return ResultUtil.success(map);
    }

}
