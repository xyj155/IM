package com.example.administrator.im;

import android.support.multidex.MultiDexApplication;

import com.mob.MobSDK;

import cn.jpush.im.android.api.JMessageClient;

/**
 * Created by Administrator on 2018/7/8.
 */

public class App extends MultiDexApplication {
    public static App app = null;

    public static App getInstance() {
        if (app == null) {
            return new App();
        } else {
            return app;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        MobSDK.init(this, "26645478e8510", "7c1e617b2b5626637ca7e106a3f84ea3");
        JMessageClient.init(this);
        JMessageClient.setDebugMode(true);
    }
}
