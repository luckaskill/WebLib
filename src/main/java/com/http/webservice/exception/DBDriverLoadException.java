package com.http.webservice.exception;

public class DBDriverLoadException extends RuntimeException {

    public DBDriverLoadException(String s) {
        super(s);
    }

    public DBDriverLoadException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public DBDriverLoadException(Throwable throwable) {
        super(throwable);
    }

    public DBDriverLoadException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}