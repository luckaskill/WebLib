package com.http.webservice.exception;

public class DBDriveLoadException extends RuntimeException {

    public DBDriveLoadException(String s) {
        super(s);
    }

    public DBDriveLoadException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public DBDriveLoadException(Throwable throwable) {
        super(throwable);
    }

    public DBDriveLoadException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
