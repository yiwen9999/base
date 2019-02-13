package com.hex.base.controller;

import com.hex.base.service.PlaceService;
import com.hex.base.util.HexUtil;
import com.hex.base.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * User: hexuan
 * Date: 2018/10/16
 * Time: 2:06 PM
 */
@CrossOrigin
@RestController
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @GetMapping("/findAllPlaceList")
    public Object findAllPlaceList(@RequestParam(defaultValue = "0") Integer page,
                                   @RequestParam(defaultValue = "500") Integer size,
                                   @RequestParam(defaultValue = "id") String sortStr,
                                   @RequestParam(defaultValue = "asc") String asc) {
        Pageable pageable = HexUtil.getPageRequest(page, size, sortStr, asc);
        return ResultUtil.success(placeService.findAllPlaceList(pageable));
    }

    @GetMapping("/findAllPlaceListByLevel")
    public Object findAllPlaceListByLevel() {
        Map<String, Object> map = new HashMap<>();
        map.put("topLevelPlaceList", placeService.findTopLevelPlaceList());
        map.put("secondLevelPlaceList", placeService.findSecondLevelPlaceList());
        return ResultUtil.success(map);
    }

}
