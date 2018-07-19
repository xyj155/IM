package com.example.administrator.im.model;

import com.example.administrator.im.contract.FragmentMapContract;
import com.example.administrator.im.gson.Active;
import com.example.administrator.im.gson.BaseGson;
import com.example.administrator.im.gson.UserGson;
import com.example.administrator.im.util.RetrofitUtil;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2018/7/15.
 */

public class FragmentMapModel implements FragmentMapContract.Model {


    @Override
    public Observable<BaseGson<List<UserGson<Active>>>> queryActive(String location) {
        return RetrofitUtil.getInstance().getServerices().queryLocationActive(location);
    }
}
