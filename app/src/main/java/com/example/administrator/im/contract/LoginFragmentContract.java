package com.example.administrator.im.contract;

import android.app.Activity;
import android.content.Context;

import com.example.administrator.im.gson.BaseGson;
import com.example.administrator.im.gson.UserGson;

import rx.Observable;

/**
 * Created by Administrator on 2018/7/15.
 */

public interface LoginFragmentContract {
    interface Model {
        Observable<BaseGson<UserGson>> login(Context context,String username, String password);
    }

    interface View {
        void showMessage(String message);

        void showLoading();

        void hideLoading();
    }

    interface Presenter {
        void login(Activity context, String username, String password);
    }
}
