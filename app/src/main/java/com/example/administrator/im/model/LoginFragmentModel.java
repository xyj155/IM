package com.example.administrator.im.model;

import android.content.Context;

import com.example.administrator.im.contract.LoginFragmentContract;
import com.example.administrator.im.gson.BaseGson;
import com.example.administrator.im.gson.UserGson;
import com.example.administrator.im.util.RetrofitUtil;

import rx.Observable;

/**
 * Created by Administrator on 2018/7/15.
 */

public class LoginFragmentModel implements LoginFragmentContract.Model {
    @Override
    public Observable<BaseGson<UserGson>> login(Context context,String username, String password) {
        return RetrofitUtil.
                getInstance().
                getServerices().
                login(username, password);
    }
}
