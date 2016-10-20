package com.overseer.cache;

import java.util.concurrent.TimeUnit;

public interface ICacheRegionProvider {

    public void saveDataInCacheRegion(String key, String id, Object value, long timeout, TimeUnit unit);

    public void setExpiryForCacheRegion(String key, long timeout, TimeUnit unit) throws CacheRegionNotFoundException;

    public Object getDataFromCacheRegion(String key, String id) throws CacheRegionNotFoundException, CacheIdNotFoundInCacheRegionException;

    public void deleteDataInCacheRegion(String key, String id) throws CacheRegionNotFoundException, CacheIdNotFoundInCacheRegionException;

    public void deleteCacheRegion(String key) throws CacheRegionNotFoundException;
}
