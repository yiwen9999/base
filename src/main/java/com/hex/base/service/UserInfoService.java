package com.hex.base.service;

import com.hex.base.domain.UserInfo;
import com.hex.base.dto.UserInfoCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * User: hexuan
 * Date: 2018/7/12
 * Time: 下午2:54
 */
public interface UserInfoService {
    UserInfo saveUserInfo(UserInfo userInfo);

    UserInfo findUserInfoById(String id);

    Page<UserInfo> findAllUserInfoList(Pageable pageable);

    void deleteUserInfoById(String id);

    UserInfo findUserInfoByPhone(String phone);

    Page<UserInfo> findUserInfoListByCondition(UserInfoCondition userInfoCondition, Pageable pageable);

    List<UserInfo> findAllUserInfoList(Sort sort);
}
