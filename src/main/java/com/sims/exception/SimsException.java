package com.sims.exception;

public abstract class SimsException extends RuntimeException {

    public SimsException() {}

    public abstract String getErrorCode();

    public SimsException(String message) {
        super(message);
    }

    public SimsException(String message, Throwable cause) {
        super(message, cause);
    }

    public SimsException(Throwable cause) {
        super(cause);
    }

    public SimsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
