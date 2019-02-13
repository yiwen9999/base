package com.hex.base.controller;

import com.hex.base.converter.UserInfo2UserInfoVOConverter;
import com.hex.base.domain.UserInfo;
import com.hex.base.dto.UserInfoCondition;
import com.hex.base.service.UserInfoService;
import com.hex.base.util.HexUtil;
import com.hex.base.util.ResultUtil;
import com.hex.base.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: hexuan
 * Date: 2018/11/6
 * Time: 2:08 PM
 */
@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/findUserInfoListByCondition")
    public Object findUserInfoListByCondition(UserInfoCondition userInfoCondition,
                                              @RequestParam(defaultValue = "0") Integer page,
                                              @RequestParam(defaultValue = "20") Integer size,
                                              @RequestParam(defaultValue = "createTime") String sortStr,
                                              @RequestParam(defaultValue = "desc") String asc) {
        Pageable pageable = HexUtil.getPageRequest(page, size, sortStr, asc);
        Page<UserInfo> userInfoPage = userInfoService.findUserInfoListByCondition(userInfoCondition, pageable);
        List<UserInfoVO> userInfoVOList = UserInfo2UserInfoVOConverter.converter(userInfoPage.getContent());
        return ResultUtil.success(new PageImpl<>(userInfoVOList, pageable, userInfoPage.getTotalElements()));
    }
}
