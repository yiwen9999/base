package com.hex.base.repository;

import com.hex.base.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: hexuan
 * Date: 2018/7/12
 * Time: 下午2:43
 */
public interface PlaceRepository extends JpaRepository<Place, String> {
}
