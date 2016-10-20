package com.overseer.session;

public class SessionExtensionFailedException extends Exception {
	
	private static final long serialVersionUID = -4843940997525911755L;

	public SessionExtensionFailedException() {
		super();
	}

	public SessionExtensionFailedException(String message) {
		super(message);
	}

	public SessionExtensionFailedException(Throwable cause) {
		super(cause);
	}

	public SessionExtensionFailedException(String string, Throwable e) {
		super(string, e);
	}

	protected SessionExtensionFailedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
