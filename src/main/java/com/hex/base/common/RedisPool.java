package com.hex.base.common;

import com.hex.base.util.PropertiesUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * User: hexuan
 * Date: 2018/4/13
 * Time: 下午5:04
 */
public class RedisPool {
    private static JedisPool pool;//jedis连接池
    private static Integer maxTotal = Integer.parseInt(PropertiesUtil.getProperty("redis.max.total", "20")); // 最大连接数
    private static Integer maxIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.max.idle", "10"));//在连接池中最大的idle（空闲）状态的jedis实例个数
    private static Integer minIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.min.idle", "2"));//在连接池中最小的idle（空闲）状态的jedis实例个数
    private static Boolean testOnBorrow = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.min.idle", "true"));//在borrow一个jedis实例的时候，是否要进行验证操作。如果值为true，则获得的实例肯定可用。
    private static Boolean testOnReturn = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.min.idle", "false"));//在return一个jedis实例的时候，是否要进行验证操作。如果值为true，则放回的实例肯定可用。
    private static String redisIp = PropertiesUtil.getProperty("redis.ip");
    private static Integer redisPort = Integer.parseInt(PropertiesUtil.getProperty("redis.port"));

    static {
        initPool();
    }

    private static void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);
        config.setBlockWhenExhausted(true); // 连接耗尽的时候，是否阻塞。false会抛出异常，true阻塞直到超时。默认为true

        pool = new JedisPool(config, redisIp, redisPort, 1000 * 2);
    }

    public static Jedis getJedis() {
        return pool.getResource();
    }

    public static void main(String[] args) {
        Jedis jedis = pool.getResource();
        jedis.set("hexkey", "hexvalue");
        jedis.close();

        pool.destroy(); // 临时调用，销毁连接池中的所有连接

        System.out.println("program is end");
    }
}
