package com.wicky.mansys.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class CustomShiroSessionDao extends CachingSessionDAO {

    @Autowired
    private ShiroSessionDao shiroSessionDao;


    @Override
    protected void doUpdate(Session session) {
        if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
            return; //如果会话过期/停止 没必要再更新了
        }
    }

    @Override
    protected void doDelete(Session session) {

    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        shiroSessionDao.saveSession(session);
        return session.getId();
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return null;
    }
}