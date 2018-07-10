package com.hex.base.service;

import com.hex.base.domain.SatelliteMeeting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * User: hexuan
 * Date: 2018/7/10
 * Time: 下午2:41
 */
public interface SatelliteMeetingService {
    SatelliteMeeting saveSatelliteMeeting(SatelliteMeeting satelliteMeeting);

    SatelliteMeeting findSatelliteMeetingById(Integer id);

    Page<SatelliteMeeting> findAllSatelliteMeetingList(Pageable pageable);

    void deleteSatelliteMeetingById(Integer id);
}
