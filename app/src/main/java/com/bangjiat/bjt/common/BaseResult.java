package com.bangjiat.bjt.common;

/**
 * Created by Administrator on 2018/4/14 0014.
 */

public class BaseResult<T> {
    /**
     * 状态 200代表成功
     */
    private int status;
    private String message;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
