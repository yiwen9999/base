package com.hex.base.repository;

import com.hex.base.domain.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: hexuan
 * Date: 2018/7/6
 * Time: 下午4:11
 */
public interface SpeakerRepository extends JpaRepository<Speaker, Integer> {
}
