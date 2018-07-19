package com.example.administrator.im.contract;

import android.app.Activity;

import com.example.administrator.im.gson.BaseGson;
import com.example.administrator.im.gson.UserGson;

import rx.Observable;

/**
 * Created by Administrator on 2018/7/15.
 */

public interface RegisterFragmentContract {
    interface Model {
        Observable<BaseGson<UserGson>> register(String username, String password, String tel);
    }

    interface View {
        void showMessage(String message);

        void showLoading();

        void hideLoading();
    }

    interface Presenter {
        void Register(Activity activity,String username, String password, String tel);
    }
}
