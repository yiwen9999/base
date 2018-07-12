package com.hex.base.repository;

import com.hex.base.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: hexuan
 * Date: 2018/7/12
 * Time: 下午2:46
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
}
