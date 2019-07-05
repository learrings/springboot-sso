package com.one.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public final class RedisUtil {

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * 普通缓存放入并设置时间
	 *
	 * @param key   键
	 * @param value 值
	 * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
	 * @return true成功 false 失败
	 */
	public boolean set(String key, Object value, long time) {
		try {
			redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
			return true;
		} catch (Exception e) {
			log.error("插入redis异常：",e);
			return false;
		}
	}

	/**
	 * 普通缓存获取
	 *
	 * @param key 键
	 * @return 值
	 */
	public Object get(String key) {
		return key == null ? null : redisTemplate.opsForValue().get(key);
	}

	/**
	 * 删除缓存
	 *
	 * @param key 可以传一个值 或多个
	 */
	public void del(String... key) {
		List<String> keyList = Stream.of(key).filter(StringUtils::isNotBlank).collect(Collectors.toList());
		if (CollectionUtils.isEmpty(keyList)) {
			return;
		}

		redisTemplate.delete(keyList);
	}

}
