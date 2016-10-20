package com.overseer.session;

public interface ISessionDataProvider {

	public void setSessionData(String sessionId, DataId dataId, Object value);

	public Object getSessionData(String sessionId, DataId dataId) throws SessionNotFoundException, DataNotFoundInSessionException;

	public void deleteSessionData(String sessionId, DataId dataId) throws SessionNotFoundException, DataNotFoundInSessionException;

	public void extendSession(String sessionId) throws SessionExtensionFailedException;

	public void deleteSession(String sessionId) throws SessionNotFoundException;

}
