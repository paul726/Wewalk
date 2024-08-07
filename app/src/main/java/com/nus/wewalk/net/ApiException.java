package com.nus.wewalk.net;

import java.io.IOException;

public class ApiException extends IOException {

    private final int code;

    public ApiException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() { return code; }
}
