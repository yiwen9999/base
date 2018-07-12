package com.hex.base.service;

import com.hex.base.domain.SatelliteMeeting;
import com.hex.base.repository.SatelliteMeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * User: hexuan
 * Date: 2018/7/10
 * Time: 下午2:43
 */
@Service
public class SatelliteMeetingServiceImpl implements SatelliteMeetingService {

    @Autowired
    private SatelliteMeetingRepository satelliteMeetingRepository;

    @Override
    public SatelliteMeeting saveSatelliteMeeting(SatelliteMeeting satelliteMeeting) {
        return satelliteMeetingRepository.save(satelliteMeeting);
    }

    @Override
    public SatelliteMeeting findSatelliteMeetingById(Integer id) {
        return satelliteMeetingRepository.findOne(id);
    }

    @Override
    public Page<SatelliteMeeting> findAllSatelliteMeetingList(Pageable pageable) {
        return satelliteMeetingRepository.findAll(pageable);
    }

    @Override
    public void deleteSatelliteMeetingById(Integer id) {
        satelliteMeetingRepository.delete(id);
    }
}
