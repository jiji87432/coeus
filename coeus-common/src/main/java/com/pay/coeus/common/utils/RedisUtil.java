package com.pay.coeus.common.utils;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

/**
 * redis工具类
 * @author yongda.ren
 *
 */
@Component
public class RedisUtil {

	private static JedisSentinelPool jedisSentinelPool;
	
	@Resource
	public void setJedisSentinelPool(JedisSentinelPool jedisSentinelPool) {
		RedisUtil.jedisSentinelPool = jedisSentinelPool;
	}

	/**
	 * @Description 查询缓存中key的值
	 * @param key
	 * @return
	 * @see 需要参考的类或方法
	 */
	public static String getValue(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisSentinelPool.getResource();
			String result = jedis.get(key);
			return result;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * @Description 设置缓存
	 * @param key
	 * @param value
	 * @param expire
	 * @see 需要参考的类或方法
	 */
	public static void setKeyValue(String key, String value, int expire) {

		Jedis jedis = null;
		try {
			jedis = jedisSentinelPool.getResource();
			jedis.set(key, value);
			if (expire > 0) {
				jedis.expire(key, expire);
			}
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * @Description 删除key
	 * @param key
	 * @see 需要参考的类或方法
	 */
	public static void removeKeyValue(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisSentinelPool.getResource();
			jedis.del(key);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * @Description 通过key向list左头部添加字符串
	 * @param key
	 * @param strs
	 * @see 需要参考的类或方法
	 */
	public static Long lpush(String key, String... strs) {
		Jedis jedis = null;
		Long res = null;
		try {
			jedis = jedisSentinelPool.getResource();
			res = jedis.lpush(key, strs);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return res;
	}

	/**
	 * @Description 通过key从list右尾部删除一个value,并返回该元素
	 * @param key
	 * @param strs
	 * @see 需要参考的类或方法
	 */
	public static String rpop(String key) {
		Jedis jedis = null;
		String res = null;
		try {
			jedis = jedisSentinelPool.getResource();
			res = jedis.rpop(key);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return res;
	}

	/**
	 * @Description 通过key查询队列key的长度
	 * @param key
	 * @return
	 * @see 需要参考的类或方法
	 */
	public static Long llen(String key) {
		Jedis jedis = null;
		Long res = null;
		try {
			jedis = jedisSentinelPool.getResource();
			res = jedis.llen(key);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return res;
	}

	/**
	 * @Description 通过key删除
	 * @param key
	 * @return
	 * @see 需要参考的类或方法
	 */
	public static Long del(String key) {
		Jedis jedis = null;
		Long res = null;
		try {
			jedis = jedisSentinelPool.getResource();
			res = jedis.del(key);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return res;
	}

	/**
	 * 通过key给field设置指定的值,如果key不存在,则先创建
	 * 
	 * @param key
	 * @param field
	 *            字段
	 * @param value
	 * @return 如果存在返回0 异常返回null
	 */
	public static Long hset(String key, String field, String value) {
		Jedis jedis = null;
		Long res = null;
		try {
			jedis = jedisSentinelPool.getResource();
			res = jedis.hset(key, field, value);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return res;
	}

	/**
	 * 通过key 删除指定的 field
	 * 
	 * @param key
	 * @param fields
	 *            可以是 一个 field 也可以是 一个数组
	 * @return
	 */
	public static Long hdel(String key, String... fields) {
		Jedis jedis = null;
		Long res = null;
		try {
			jedis = jedisSentinelPool.getResource();
			res = jedis.hdel(key, fields);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return res;
	}

	/**
	 * 通过key获取所有的field和value
	 * 
	 * @param key
	 * @return
	 */
	public static Map<String, String> hgetall(String key) {
		Jedis jedis = null;
		Map<String, String> res = null;
		try {
			jedis = jedisSentinelPool.getResource();
			res = jedis.hgetAll(key);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return res;
	}

	/**
	 * 通过key返回所有和key有关的value
	 * 
	 * @param key
	 * @return
	 */
	public static List<String> hvals(String key) {
		Jedis jedis = null;
		List<String> res = null;
		try {
			jedis = jedisSentinelPool.getResource();
			res = jedis.hvals(key);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return res;
	}

	/**
	 * 通过key 和 field 获取指定的 value
	 * 
	 * @param key
	 * @param field
	 * @return 没有返回null
	 */
	public static String hget(String key, String field) {
		Jedis jedis = null;
		String res = null;
		try {
			jedis = jedisSentinelPool.getResource();
			res = jedis.hget(key, field);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return res;
	}

	public static Long setnx(String key, String value) {
		Jedis jedis = null;
		Long res = null;
		try {
			jedis = jedisSentinelPool.getResource();
			res = jedis.setnx(key, value);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return res;
	}
	
	public static Long expire(String key, int seconds) {
		Jedis jedis = null;
		Long res = null;
		try {
			jedis = jedisSentinelPool.getResource();
			res = jedis.expire(key, seconds);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return res;
	}
	public static Long decr(String key) {
		Jedis jedis = null;
		Long res = null;
		try {
			jedis = jedisSentinelPool.getResource();
			res = jedis.decr(key);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return res;
	}
	
	/**
	 * 使用key存入缓存值并设置超时时间
	 * @param key
	 * @param seconds
	 * @return
	 */
	public static Long incr(String key, Integer seconds) {
		Jedis jedis = null;
		Long res = null;
		try {
			jedis = jedisSentinelPool.getResource();
			res = jedis.incr(key);
			if(seconds != null){
				jedis.expire(key, seconds);
			}
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return res;
	}
}
