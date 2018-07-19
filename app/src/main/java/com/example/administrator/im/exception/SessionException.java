package com.example.administrator.im.exception;

import java.io.IOException;

/**
 * Created by Administrator on 2018/7/15.
 */

public class SessionException extends IOException {
    public SessionException() {
        this("登录超时,请重新登录");
    }

    public SessionException(String message) {
        super(message);
    }
}