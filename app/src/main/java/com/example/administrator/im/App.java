package com.example.administrator.im;

import android.app.Application;

import cn.jpush.im.android.api.JMessageClient;

/**
 * Created by Administrator on 2018/7/8.
 */

public class App extends Application {
    public static  App app = null;
    public static App getInstance(){
        if (app==null){
            return new App();
        }else {
            return app;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        JMessageClient.init(this);
        JMessageClient.setDebugMode(true);
    }
}
