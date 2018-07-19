package com.example.administrator.im.gson;

/**
 * Created by Administrator on 2018/7/15.
 */

public class BaseGson<T> {
    private boolean issuccess;
    private int code;
    private T data;


    @Override
    public String toString() {
        return "BaseGson{" +
                "issuccess=" + issuccess +
                ", code=" + code +
                ", data=" + data +
                '}';
    }

    public boolean issuccess() {
        return issuccess;
    }

    public void setIssuccess(boolean issuccess) {
        this.issuccess = issuccess;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
