package com.hex.base.service;

import com.hex.base.domain.Place;
import com.hex.base.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * User: hexuan
 * Date: 2018/7/12
 * Time: 下午2:48
 */
@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Override
    public Place savePlace(Place place) {
        return placeRepository.save(place);
    }

    @Override
    public Place findPlaceById(String id) {
        return placeRepository.findOne(id);
    }

    @Override
    public Page<Place> findAllPlaceList(Pageable pageable) {
        return placeRepository.findAll(pageable);
    }

    @Override
    public void deletePlaceById(String id) {
        placeRepository.delete(id);
    }
}
