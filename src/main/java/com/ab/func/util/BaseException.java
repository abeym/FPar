package com.ab.func.util;

public class BaseException extends Exception {

	public BaseException() {
		super();
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public BaseException(String cause) {
		super(cause);
	}

}
