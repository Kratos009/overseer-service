package com.overseer.cache;

import java.util.concurrent.TimeUnit;

public interface ICacheProvider {
	
	public void saveData(String key, Object value, long timeout, TimeUnit unit, CachePriority priority);
	
	public Object getData(String key) throws CacheKeyNotFoundException;
	
	public void setExpiry(String key, long timeout, TimeUnit unit) throws CacheKeyNotFoundException;
	
	public void deleteData(String key) throws CacheKeyNotFoundException;
		
}
 