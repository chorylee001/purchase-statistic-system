package com.infowoo.purchase.mapper;

import com.infowoo.purchase.entity.UserRoleInfo;

public interface UserRoleInfoMapper {
    int insert(UserRoleInfo record);

    int insertSelective(UserRoleInfo record);
}