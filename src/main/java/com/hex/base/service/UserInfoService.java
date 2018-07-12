package com.hex.base.service;

import com.hex.base.domain.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
}
