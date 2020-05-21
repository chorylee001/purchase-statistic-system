package com.infowoo.purchase.model;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Created by WuKong on 2018/12/19.
 */
public class JWTToken implements AuthenticationToken {
    // 密钥
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
