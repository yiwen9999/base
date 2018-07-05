package com.hex.base.controller;

import com.hex.base.domain.Channel;
import com.hex.base.domain.Operator;
import com.hex.base.enums.ResultEnum;
import com.hex.base.service.ChannelService;
import com.hex.base.service.OperatorService;
import com.hex.base.util.HexUtil;
import com.hex.base.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 频道controller
 * User: hexuan
 * Date: 2017/8/16
 * Time: 下午2:13
 */
@RestController
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @Autowired
    private OperatorService operatorService;

    /**
     * 获取频道集合
     *
     * @return 顶层频道集合（topChannelList）；子频道集合（childChannelList）
     */
    @GetMapping(value = "/getChannelList")
    public Object getChannelList() {
        Map<String, Object> map = new HashMap<>();
        List<Channel> topChannelList = channelService.findChannelsByParentChannelIsNullOrderBySort();
        List<Channel> childChannelList = new ArrayList<>();
        for (Channel topChannel : topChannelList) {
            if (null != topChannel.getChildChannels() && !topChannel.getChildChannels().isEmpty()) {
                childChannelList.addAll(topChannel.getChildChannels());
            }
        }
        map.put("topChannelList", topChannelList);
        map.put("childChannelList", childChannelList);
        return ResultUtil.success(map);
    }

    /**
     * 获取在用频道集合
     *
     * @return 在用顶层频道集合（topChannelList）；在用子频道集合（childChannelList）
     */
    @GetMapping(value = "/getUsingChannelList")
    public Object getUsingChannelList() {
        Map<String, Object> map = new HashMap<>();
        List<Channel> topChannelList = channelService.findChannelsByParentChannelIsNullAndStateOrderBySort(new Integer(2));
        List<Channel> childChannelList = new ArrayList<>();
        for (Channel topChannel : topChannelList) {
            if (null != topChannel.getUsingChannels() && !topChannel.getUsingChannels().isEmpty()) {
                childChannelList.addAll(topChannel.getUsingChannels());
            }
        }
        map.put("topChannelList", topChannelList);
        map.put("childChannelList", childChannelList);
        return ResultUtil.success(map);
    }

    /**
     * 保存频道
     *
     * @param channel 频道
     * @return
     */
    @PostMapping(value = "/saveChannel")
    public Object saveChannel(@Valid Channel channel) {
        if (channel.getParentChannel() != null && channel.getParentChannel().getId() != null && !channel.getParentChannel().getId().equals("") && !channel.getParentChannel().getId().equals("root"))
            channel.setParentChannel(channel.getParentChannel());
        else
            channel.setParentChannel(null);
        channel.setName(channel.getName());
        channel.setUrl(channel.getUrl());
        channel.setSort(channel.getSort());
        return ResultUtil.success(channelService.saveChannel(channel));
    }

    /**
     * 根据频道id删除频道
     *
     * @param channel 频道id
     * @return
     */
    @PostMapping(value = "/deleteChannel")
    public Object deleteChannel(Channel channel) {
        channelService.deleteChannel(channel);
        return ResultUtil.success();
    }

    /**
     * 根据频道id获取频道信息
     *
     * @param id 频道id
     * @return
     */
    @PostMapping(value = "/getChannelInfo")
    public Object getChannelInfo(String id) {
        return ResultUtil.success(channelService.findChannelById(id));
    }

    /**
     * 修改频道
     *
     * @param name 频道名称
     * @param url  频道路径
     * @param sort 频道排序
     * @param id   频道id
     * @return
     */
    @PostMapping(value = "/updateChannel")
    public Object updateChannel(String name, String url, Integer sort, String id) {
        Channel channel = channelService.findChannelById(id);
        channel.setName(name);
        channel.setUrl(url);
        channel.setSort(sort);
        return ResultUtil.success(channelService.saveChannel(channel));
    }

    /**
     * 根据父频道id集合和操作人身份所能访问频道限制，返回对应能访问的子频道集合
     *
     * @param ids     父频道id集合
     * @param request request获取操作人员
     * @return
     */
    @PostMapping(value = "/getChildChannelByParentIds")
    public Object getChildChannelByParentIds(@RequestParam String[] ids, HttpServletRequest request) {
        Operator operator = HexUtil.getOperator(request);
        if (operator != null) {
            operator = operatorService.findOperatorById(operator.getId());
            if (null != operator.getRole()) {
                return ResultUtil.success(channelService.findChannelsByParentChannelIdsAndLimitChannels(ids, operator.getRole().getUsingChannels()));
            } else if (operator.getId().equals("root")) {
                return ResultUtil.success(channelService.findChannelsByParentChannelIdsAndLimitChannels(ids, null));
            } else {
                return ResultUtil.error(ResultEnum.NO_ROLE.getCode(), ResultEnum.NO_ROLE.getMsg());
            }
        } else {
            return ResultUtil.error(ResultEnum.UN_LOGIN.getCode(), ResultEnum.UN_LOGIN.getMsg());
        }
    }
}
