package com.overseer.session;

public class DataNotFoundInSessionException extends Exception{

	private static final long serialVersionUID = -4620297069592330000L;

	public DataNotFoundInSessionException() {
		super();
	}

	public DataNotFoundInSessionException(String message) {
		super(message);
	}

	public DataNotFoundInSessionException(Throwable cause) {
		super(cause);
	}

	public DataNotFoundInSessionException(String string, Throwable e) {
		super(string, e);
	}

	protected DataNotFoundInSessionException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
