package com.hex.base.service;

import com.hex.base.domain.UserInfo;
import com.hex.base.dto.UserInfoCondition;
import com.hex.base.repository.MySpec;
import com.hex.base.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public UserInfo findUserInfoByPhone(String phone) {
        return userInfoRepository.findFirstByPhone(phone);
    }

    @Override
    public Page<UserInfo> findUserInfoListByCondition(UserInfoCondition userInfoCondition, Pageable pageable) {
        return userInfoRepository.findAll(MySpec.findUserInfos(userInfoCondition), pageable);
    }

    @Override
    public List<UserInfo> findAllUserInfoList(Sort sort) {
        return userInfoRepository.findAll(sort);
    }
}
