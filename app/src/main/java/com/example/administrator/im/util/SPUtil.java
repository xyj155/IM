package com.example.administrator.im.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.administrator.im.App;

import java.util.Iterator;
import java.util.Map;


/**
 * Created by Administrator on 2018/7/15.
 */

public class SPUtil {
    public static App app;

    /**
     * 获取上下文
     * @return
     */
    public static Context getApplicationContext() {
        if (App.getInstance().getApplicationContext() == null) {
            return new App();
        }
        return App.getInstance().getApplicationContext();
    }


    public static void saveUserInfor(Map<String, ?> values) {
        SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE).edit();
        Iterator it = values.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String key = entry.getKey().toString();
            if (entry.getValue() instanceof Boolean){
                editor.putBoolean(key, ((Boolean) entry.getValue()).booleanValue());
            }else if (entry.getValue() instanceof String ){
                editor.putString(key,  entry.getValue().toString());
            }
        }
        editor.apply();
    }

    public static Object getSPValue(String key) {
        SharedPreferences sp = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        Map<String, ?> all = sp.getAll();
        Object object = all.get(key);
        return object;
    }
}
