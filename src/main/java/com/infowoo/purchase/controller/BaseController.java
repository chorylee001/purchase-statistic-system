package com.infowoo.purchase.controller;

import com.infowoo.purchase.entity.UserInfo;
import com.infowoo.purchase.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;

import javax.annotation.Resource;

public class BaseController {


    @Resource
    private IUserService userService;


    /**
     * 获取公司id
     * @return
     */
    Long getCompanyId(){
        return getUserBaseInfo().getCompanyId();
    }

    /**
     * 获取用户id
     * @return
     */
    UserInfo getUserBaseInfo(){
        if(SecurityUtils.getSubject().isAuthenticated()){
            String username = (String)SecurityUtils.getSubject().getPrincipal();
//            String username = JWTUtil.getUsername(token);
            UserInfo userInfo = userService.getUserBaseInfo(username);
            if(userInfo ==null){
                throw new UnauthenticatedException("userInfo is null");
            }
            return userInfo;
        }else {
            throw new UnauthenticatedException("is not authenticated");
        }
    }


}
