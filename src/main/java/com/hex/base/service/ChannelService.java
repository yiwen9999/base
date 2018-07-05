package com.hex.base.service;

import com.hex.base.domain.Channel;

import java.util.List;

/**
 * 频道 service
 */
public interface ChannelService {

    Channel saveChannel(Channel channel);

    Channel findChannelById(String id);

    List<Channel> findAllChannelList();

    void deleteChannel(Channel channel);

    List<Channel> findChannelListByState(Integer integer);

    /**
     * 根据id更新频道名称，url，排序
     *
     * @param name
     * @param url
     * @param sort
     * @param id
     * @return
     */
    int updateChannelNameUrlSortById(String name, String url, Integer sort, String id);

    /**
     * 根据id数组集合查询频道集合，根据创建时间升序排序
     *
     * @param ids
     * @return
     */
    List<Channel> findChannelsByIdInOrderByCreateTimeAsc(String[] ids);

    /**
     * 获取所有顶级频道，按排序号排序
     *
     * @return
     */
    List<Channel> findChannelsByParentChannelIsNullOrderBySort();

    /**
     * 获取所有子级频道，按排序号排序
     *
     * @return
     */
    List<Channel> findChannelsByParentChannelIsNotNullOrderBySort();

    /**
     * 根据状态查询顶级频道，按序号排序
     *
     * @param state
     * @return
     */
    List<Channel> findChannelsByParentChannelIsNullAndStateOrderBySort(Integer state);

    /**
     * 根据id数组集合，查询父频道id在该数组内且在限制频道集合内的频道集合
     *
     * @param ids
     * @param litmitChannels
     * @return
     */
    List<Channel> findChannelsByParentChannelIdsAndLimitChannels(String[] ids, List<Channel> litmitChannels);
}
