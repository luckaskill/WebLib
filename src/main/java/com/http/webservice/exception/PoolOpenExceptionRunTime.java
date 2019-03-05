package com.http.webservice.exception;

public class PoolOpenExceptionRunTime extends RuntimeException {
    public PoolOpenExceptionRunTime(String message) {
        super(message);
    }

    public PoolOpenExceptionRunTime(String message, Throwable cause) {
        super(message, cause);
    }

    public PoolOpenExceptionRunTime(Throwable cause) {
        super(cause);
    }

    public PoolOpenExceptionRunTime(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
