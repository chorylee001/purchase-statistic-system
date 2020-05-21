package com.infowoo.purchase.service.impl;

import com.infowoo.purchase.entity.UserInfo;
import com.infowoo.purchase.mapper.UserBaseInfoMapper;
import com.infowoo.purchase.service.IUserService;
import com.infowoo.purchase.utils.EncryptUtils;
import com.infowoo.purchase.utils.JWTUtil;
import com.infowoo.purchase.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {



    @Autowired
    private UserBaseInfoMapper userBaseInfoMapper;

    @Override
    public UserInfo getUserBaseInfo(String userName){
        return userBaseInfoMapper.findUserByUsername(userName);
    }

    @Override
    public UserInfoVo login(String username,String password){
        UserInfo userInfo = null;
        try {
            userInfo = getUserBaseInfo(username);
            if(EncryptUtils.verify(password, userInfo.getSalt(), userInfo.getPassword())){
                String token = JWTUtil.sign(username, userInfo.getPassword());
                UserInfoVo userInfoVo = UserInfoVo.builder()
                        .companyId(userInfo.getStationId())
                        .companyName(userInfo.getCompanyInfo().getCompanyName())
                        .name(userInfo.getRealName())
                        .userName(userInfo.getUsername())
                        .userId(userInfo.getId())
                        .token(token)
                        .build();
                return userInfoVo;
            }else {
                throw new IllegalArgumentException("用戶名或密码错误");
            }
        }catch (IllegalArgumentException e){
            throw e;
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new UnauthenticatedException();
        }
    }
    @Override
    public void logout(String username, String token){

    }

    @Override
    public int saveOrUpdate(UserInfo userInfo){

        if(Objects.isNull(userInfo.getId())){
            return userBaseInfoMapper.insert(userInfo);
        }else{
            return userBaseInfoMapper.update(userInfo);
        }
    }
}
