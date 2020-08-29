package com.dwp.location.exception;

public class DWPInvalidDataException extends RuntimeException {

    public DWPInvalidDataException() {
    }

    public DWPInvalidDataException(String message) {
        super(message);
    }

    public DWPInvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public DWPInvalidDataException(Throwable cause) {
        super(cause);
    }

    public DWPInvalidDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
