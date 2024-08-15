package com.nus.wewalk.net;

public final class ApiResponse<T> {

    private int code;
    private String msg;
    private T data;
    private T rows;

    public ApiResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public T getRows() {
        return rows;
    }

}
