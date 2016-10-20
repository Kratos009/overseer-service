package com.overseer.cache;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public class RedisCacheProvider implements ICacheProvider, ICacheRegionProvider {

	private RedisTemplate<String, Object> redisTemplate;
	
	public RedisCacheProvider(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public void saveData(String key, Object value, long timeout, TimeUnit unit, CachePriority priority) {
		redisTemplate.opsForValue().set(key, value);
		redisTemplate.expire(key, timeout, unit);
	}

	@Override
	public Object getData(String key) throws CacheKeyNotFoundException {
		if (!redisTemplate.hasKey(key))
			throw new CacheKeyNotFoundException();
		Object value = redisTemplate.opsForValue().get(key);
		return value;
	}
	
	@Override
	public void setExpiry(String key, long timeout, TimeUnit unit) throws CacheKeyNotFoundException {
		if (!redisTemplate.hasKey(key))
			throw new CacheKeyNotFoundException();
		redisTemplate.expire(key, timeout, unit);
	}

	@Override
	public void deleteData(String key) throws CacheKeyNotFoundException {
		if (!redisTemplate.hasKey(key))
			throw new CacheKeyNotFoundException();
		redisTemplate.delete(key);
	}

	@Override
	public void saveDataInCacheRegion(String key, String id, Object value, long timeout, TimeUnit unit) {
		redisTemplate.opsForHash().put(key, id, value);	
		redisTemplate.expire(key, timeout, unit);
	}

	@Override
	public void setExpiryForCacheRegion(String key, long timeout, TimeUnit unit) throws CacheRegionNotFoundException {
		if(!redisTemplate.hasKey(key)) throw new CacheRegionNotFoundException();
		redisTemplate.expire(key, timeout, unit);
	}

	@Override
	public Object getDataFromCacheRegion(String key, String id) throws CacheRegionNotFoundException, CacheIdNotFoundInCacheRegionException {
		if(!redisTemplate.hasKey(key)) throw new CacheRegionNotFoundException();
		if(!redisTemplate.opsForHash().hasKey(key, id)) throw new CacheIdNotFoundInCacheRegionException();
		Object value = redisTemplate.opsForHash().get(key, id);
		return value;
	}

	@Override
	public void deleteDataInCacheRegion(String key, String id) throws CacheRegionNotFoundException, CacheIdNotFoundInCacheRegionException {
		if(!redisTemplate.hasKey(key)) throw new CacheRegionNotFoundException();
		if(!redisTemplate.opsForHash().hasKey(key, id)) throw new CacheIdNotFoundInCacheRegionException();
		redisTemplate.opsForHash().delete(key, id);
	}

	@Override
	public void deleteCacheRegion(String key) throws CacheRegionNotFoundException {
		if(!redisTemplate.hasKey(key)) throw new CacheRegionNotFoundException();
		redisTemplate.delete(key);
	}

}
