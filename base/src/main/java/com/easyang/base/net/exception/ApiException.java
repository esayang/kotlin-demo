package com.easyang.base.net.exception;


/**
 * Created by SC16004984 on 2018/2/8.
 */

public class ApiException extends Exception {
    public ErrorType type;
    public String msg;

    public ApiException(Throwable throwable, ErrorType type) {
        super(throwable);
        this.type = type;
    }

    @Override
    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }

    public ErrorType getType() {
        return type;
    }
}
