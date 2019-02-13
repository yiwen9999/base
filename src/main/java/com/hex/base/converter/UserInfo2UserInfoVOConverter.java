package com.hex.base.converter;

import com.hex.base.domain.UserInfo;
import com.hex.base.vo.UserInfoVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * User: hexuan
 * Date: 2018/7/12
 * Time: 下午4:41
 */
public class UserInfo2UserInfoVOConverter {
    public static UserInfoVO converter(UserInfo userInfo) {
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(userInfo, userInfoVO);
        return userInfoVO;
    }

    public static List<UserInfoVO> converter(List<UserInfo> userInfoList) {
        List<UserInfoVO> userInfoVOList = new ArrayList<>();
        for (UserInfo userInfo : userInfoList) {
            userInfoVOList.add(converter(userInfo));
        }
        return userInfoVOList;
    }
}
