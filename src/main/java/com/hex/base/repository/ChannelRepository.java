package com.hex.base.repository;

import com.hex.base.domain.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChannelRepository extends JpaRepository<Channel, String> {
    List<Channel> findChannelsByStateOrderByCreateTimeAsc(Integer state);

    @Modifying
    @Query("update Channel c set c.name=?1,c.url=?2,c.sort=?3 where c.id=?4")
    int updateChannelNameUrlSortById(String name, String url, Integer sort, String id);

    List<Channel> findChannelsByIdInOrderByCreateTimeAsc(String[] ids);

    List<Channel> findChannelsByParentChannelIsNullOrderBySort();

    List<Channel> findChannelsByParentChannelIsNotNullOrderBySort();

    List<Channel> findChannelsByParentChannelIsNullAndStateOrderBySort(Integer state);
}
