package com.hex.base.repository;

import com.hex.base.domain.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * User: hexuan
 * Date: 2018/7/6
 * Time: 下午4:11
 */
public interface SpeakerRepository extends JpaRepository<Speaker, Integer>, JpaSpecificationExecutor {
    List<Speaker> findSpeakersByIdInOrderById(List<Integer> speakerIdList);
}
