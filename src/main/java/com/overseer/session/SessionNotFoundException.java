package com.overseer.session;

public class SessionNotFoundException extends Exception {

	private static final long serialVersionUID = -4553172759215213851L;

	public SessionNotFoundException() {
		super();
	}

	public SessionNotFoundException(String message) {
		super(message);
	}

	public SessionNotFoundException(Throwable cause) {
		super(cause);
	}

	public SessionNotFoundException(String string, Throwable e) {
		super(string, e);
	}

	protected SessionNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
