package com.hex.base.service;

import com.hex.base.domain.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * User: hexuan
 * Date: 2018/7/12
 * Time: 下午2:47
 */
public interface PlaceService {
    Place savePlace(Place place);

    Place findPlaceById(String id);

    Page<Place> findAllPlaceList(Pageable pageable);

    void deletePlaceById(String id);
}
