package com.hex.base.service;

import com.hex.base.domain.UserInfo;
import com.hex.base.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * User: hexuan
 * Date: 2018/7/12
 * Time: 下午2:56
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo saveUserInfo(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    @Override
    public UserInfo findUserInfoById(String id) {
        return userInfoRepository.findOne(id);
    }

    @Override
    public Page<UserInfo> findAllUserInfoList(Pageable pageable) {
        return userInfoRepository.findAll(pageable);
    }

    @Override
    public void deleteUserInfoById(String id) {
        userInfoRepository.delete(id);
    }
}
