package com.infowoo.purchase.dao;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.stereotype.Component;
import java.io.Serializable;

@Slf4j
@Component
public class CustomSessionDao extends EnterpriseCacheSessionDAO {

    /**
     * 创建session
     */
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        System.out.println("sessionId: " + sessionId);

        return sessionId;
    }

    /**
     * 获取session
     * @param sessionId
     * @return
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = super.doReadSession(sessionId);
        return session;
    }


    /**
     * 删除失效session
     */
    @Override
    protected void doDelete(Session session) {
        super.doDelete(session);
    }
}
