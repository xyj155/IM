package com.example.administrator.im.model;

import com.example.administrator.im.contract.RegisterFragmentContract;
import com.example.administrator.im.gson.BaseGson;
import com.example.administrator.im.gson.UserGson;
import com.example.administrator.im.util.RetrofitUtil;

import rx.Observable;

/**
 * Created by Administrator on 2018/7/15.
 */

public class RegisterFragmentModel implements RegisterFragmentContract.Model {


    @Override
    public Observable<BaseGson<UserGson>> register( String username, String password, String tel) {
        return RetrofitUtil.
                getInstance().
                getServerices().
                register(username, password, tel);
    }
}
