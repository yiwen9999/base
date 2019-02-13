package com.hex.base.repository;

import com.hex.base.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * User: hexuan
 * Date: 2018/7/12
 * Time: 下午2:43
 */
public interface PlaceRepository extends JpaRepository<Place, String> {
    List<Place> findPlacesByParentIdIsNullOrderById();

    List<Place> findPlacesByParentIdIsNotNullOrderById();
}
