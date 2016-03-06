package com.wicky.mansys.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Created by YangChao on 2015/5/19.
 */
public class RedisDao {
    @Autowired
    private ShardedJedisPool shardedJedisPool;

    public ShardedJedis getJedis() {
        return shardedJedisPool.getResource();
    }

    public void set(String key, String value)  {
        getJedis().set(key, value);
    }

    public String get(String key)  {
        return getJedis().get(key);
    }

    public byte[] get(byte[] key) {
        return getJedis().get(key);
    }

    public void set(byte[] key, byte[] value)  {
        getJedis().set(key, value);
    }
    public void set(byte[] key, byte[] value, int seconds) {
        getJedis().set(key, value);
        getJedis().expire(key, seconds);
    }

    public void delete(byte[] key) {
        getJedis().del(key);
    }

}
