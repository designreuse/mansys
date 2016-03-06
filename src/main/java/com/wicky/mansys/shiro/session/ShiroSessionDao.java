package com.wicky.mansys.shiro.session;

import com.wicky.mansys.cache.redis.RedisDao;
import com.wicky.mansys.util.SerializeUtil;
import com.cares.biz.web.Constants;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by YangChao on 2015/6/5.
 */
public class ShiroSessionDao {

    @Autowired
    private RedisDao redisDao;

    public void saveSession(Session session) {
        if (session == null || session.getId() == null)
            throw new NullPointerException("session is empty");
        try {
            byte[] key = SerializeUtil.serialize(buildRedisSessionKey(session.getId()));
            byte[] value = SerializeUtil.serialize(session);
            redisDao.set(key, value, 14400);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("save session error");
        }
    }

    public void deleteSession(Serializable id) {
        if (id == null) {
            throw new NullPointerException("session id is empty");
        }
        try {
            redisDao.delete(SerializeUtil.serialize(buildRedisSessionKey(id)));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("delete session error");
        }
    }

    public Session getSession(Serializable id) {
        if (id == null)
            throw new NullPointerException("session id is empty");
        Session session = null;
        try {
            byte[] value = redisDao.get(SerializeUtil.serialize(buildRedisSessionKey(id)));
            session = SerializeUtil.deserialize(value, Session.class);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("get session error");
        }
        return session;
    }

    public Collection<Session> getAllSessions() {
        System.out.println("get all sessions");
        return null;
    }

    private String buildRedisSessionKey(Serializable sessionId) {
        return Constants.REDIS_SHIRO_SESSION + sessionId;
    }

}