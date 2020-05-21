package com.infowoo.purchase.service;

import com.infowoo.purchase.entity.UserInfo;
import com.infowoo.purchase.vo.UserInfoVo;

public interface IUserService {

    UserInfo getUserBaseInfo(String userName);

    UserInfoVo login(String username, String password);
    void logout(String username, String token);

    int saveOrUpdate(UserInfo userInfo);
}
