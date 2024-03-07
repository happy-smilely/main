package com.happy.smilely.main_project.exception;

public class BaseRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
    public BaseRuntimeException(String message) {
        super(message);
    }
}
