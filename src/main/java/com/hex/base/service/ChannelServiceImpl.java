package com.hex.base.service;

import com.hex.base.domain.Channel;
import com.hex.base.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * 频道service实现
 * <p>
 * User: hexuan
 * Date: 2017/8/14
 * Time: 下午1:09
 */
@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

    @Override
    public Channel saveChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    @Override
    public Channel findChannelById(String id) {
        return channelRepository.findOne(id);
    }

    @Override
    public List<Channel> findAllChannelList() {
        return channelRepository.findAll();
    }

    @Override
    public void deleteChannel(Channel channel) {
        channelRepository.delete(channel);
    }

    @Override
    public List<Channel> findChannelListByState(Integer integer) {
        return channelRepository.findChannelsByStateOrderByCreateTimeAsc(integer);
    }

    @Transactional
    @Override
    public int updateChannelNameUrlSortById(String name, String url, Integer sort, String id) {
        return channelRepository.updateChannelNameUrlSortById(name, url, sort, id);
    }

    @Override
    public List<Channel> findChannelsByIdInOrderByCreateTimeAsc(String[] ids) {
        return channelRepository.findChannelsByIdInOrderByCreateTimeAsc(ids);
    }

    @Override
    public List<Channel> findChannelsByParentChannelIsNullOrderBySort() {
        return channelRepository.findChannelsByParentChannelIsNullOrderBySort();
    }

    @Override
    public List<Channel> findChannelsByParentChannelIsNotNullOrderBySort() {
        return channelRepository.findChannelsByParentChannelIsNotNullOrderBySort();
    }

    @Override
    public List<Channel> findChannelsByParentChannelIsNullAndStateOrderBySort(Integer state) {
        return channelRepository.findChannelsByParentChannelIsNullAndStateOrderBySort(state);
    }

    @Override
    public List<Channel> findChannelsByParentChannelIdsAndLimitChannels(String[] ids, List<Channel> limitChannels) {
        List<Channel> parentChannels = channelRepository.findChannelsByIdInOrderByCreateTimeAsc(ids);
        List<Channel> childChannels = new ArrayList<>();
        List<Channel> tempChannels;
        for (int i = 0; i < parentChannels.size(); i++) {
            tempChannels = parentChannels.get(i).getUsingChannels();
            if (null != tempChannels && !tempChannels.isEmpty()) {
                if (null != limitChannels) {
                    tempChannels.retainAll(limitChannels);
                }
                childChannels.addAll(tempChannels);
            }
        }
        return childChannels;
    }
}
