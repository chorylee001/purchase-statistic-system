package com.infowoo.purchase.utils;

import com.infowoo.purchase.entity.UserInfo;
import org.apache.shiro.SecurityUtils;

import javax.servlet.http.HttpServletRequest;

public class UserUtil {

    /**
     * 用户KEY
     */
    public static final String USER_KEY = "_user_key";
    /**
     * 站点KEY
     */
    public static final String SITE_KEY = "_site_key";

    /**
     * 获得用户
     *
     * @param request
     * @return
     */
    public static UserInfo getUser(HttpServletRequest request) {
        return (UserInfo) request.getAttribute(USER_KEY);
    }

    public static UserInfo getUser(){
        return (UserInfo) SecurityUtils.getSubject().getSession().getAttribute("user_info");
    }

    /**
     * 获得用户ID
     *
     * @param request
     * @return
     */
    public static Long getUserId(HttpServletRequest request) {
        UserInfo user = getUser(request);
        if (user != null) {
            return user.getId();
        } else {
            return null;
        }
    }

    /**
     * 设置用户
     *
     * @param request
     * @param user
     */
    public static void setUser(HttpServletRequest request, UserInfo user) {
        request.setAttribute(USER_KEY, user);
    }
}
