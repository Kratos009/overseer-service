package com.overseer.session;

import com.overseer.cache.CacheIdNotFoundInCacheRegionException;
import com.overseer.cache.CacheRegionNotFoundException;
import com.overseer.cache.ICacheRegionProvider;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class DistributedCacheSessionProvider implements ISessionDataProvider {

	private ICacheRegionProvider cacheRegionProvider;
	private TimeUnit sessionTimeoutUnit;
	private int timeout;
	private static Logger log = Logger.getLogger(DistributedCacheSessionProvider.class);

	public DistributedCacheSessionProvider(ICacheRegionProvider cacheRegionProvider, int timeout,
			TimeUnit sessionTimeoutUnit) {
		this.cacheRegionProvider = cacheRegionProvider;
		this.sessionTimeoutUnit = sessionTimeoutUnit;
		this.timeout = timeout;
	}

	@Override
	public void setSessionData(String sessionId, DataId dataId, Object value) {
		cacheRegionProvider.saveDataInCacheRegion(sessionId, dataId.toString(), value, timeout, sessionTimeoutUnit);
	}

	@Override
	public Object getSessionData(String sessionId, DataId dataId)
			throws SessionNotFoundException, DataNotFoundInSessionException {
		if (sessionId == null)
			throw new IllegalArgumentException("Session Id cannot be null");
		Object data;
		try {
			data = cacheRegionProvider.getDataFromCacheRegion(sessionId, dataId.toString());
		} catch (CacheRegionNotFoundException e) {
			throw new SessionNotFoundException(String.format("Session Id: %s", sessionId), e);
		} catch (CacheIdNotFoundInCacheRegionException e) {
			throw new DataNotFoundInSessionException(String.format("Session Id: %s", sessionId), e);
		}
		try {
			cacheRegionProvider.setExpiryForCacheRegion(sessionId, timeout, sessionTimeoutUnit);
		} catch (CacheRegionNotFoundException e) {
			log.error(e);
		}
		return data;
	}

	@Override
	public void deleteSessionData(String sessionId, DataId dataId)
			throws SessionNotFoundException, DataNotFoundInSessionException {
		if (sessionId == null)
			throw new IllegalArgumentException("Session Id cannot be null");
		try {
			cacheRegionProvider.deleteDataInCacheRegion(sessionId, dataId.toString());
		} catch (CacheRegionNotFoundException e) {
			throw new SessionNotFoundException(String.format("Session Id: %s", sessionId), e);
		} catch (CacheIdNotFoundInCacheRegionException e) {
			throw new DataNotFoundInSessionException(String.format("Session Id: %s", sessionId), e);
		}
	}

	@Override
	public void extendSession(String sessionId) throws SessionExtensionFailedException {
		if (sessionId == null)
			throw new IllegalArgumentException("Session Id cannot be null");
		try {
			cacheRegionProvider.setExpiryForCacheRegion(sessionId, timeout, sessionTimeoutUnit);
		} catch (Exception e) {
			throw new SessionExtensionFailedException(e);
		}
	}

	@Override
	public void deleteSession(String sessionId) throws SessionNotFoundException {
		if (sessionId == null)
			throw new IllegalArgumentException("Session Id cannot be null");
		try {
			cacheRegionProvider.deleteCacheRegion(sessionId);
		} catch (CacheRegionNotFoundException e) {
			throw new SessionNotFoundException(String.format("Session Id: %s", sessionId), e);
		}

	}

}
