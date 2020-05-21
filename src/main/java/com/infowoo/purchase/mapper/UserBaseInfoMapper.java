package com.infowoo.purchase.mapper;

import com.infowoo.purchase.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserBaseInfoMapper {
    int insert(UserInfo userInfo);

    int update(UserInfo userInfo);

    int insertSelective(UserInfo record);

    UserInfo findUserByUsername(@Param("userName") String userName);

    int getCount(@Param("companyId") Long companyId);

    List<UserInfo> getList(@Param("start") Integer start, @Param("pageSize") Integer pageSize, @Param("companyId") Long companyId);


}