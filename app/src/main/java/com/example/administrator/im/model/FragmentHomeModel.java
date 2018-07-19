package com.example.administrator.im.model;

import com.example.administrator.im.contract.FragmentHomeContract;
import com.example.administrator.im.entity.BannerGson;
import com.example.administrator.im.gson.BaseGson;
import com.example.administrator.im.gson.IcoGson;
import com.example.administrator.im.util.RetrofitUtil;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2018/7/16.
 */

public class FragmentHomeModel implements FragmentHomeContract.Model {
    @Override
    public Observable<BaseGson<List<BannerGson>>> getBannerList() {
        return RetrofitUtil.
                getInstance().
                getServerices().
                getBannerURLlist();
    }

    @Override
    public Observable<BaseGson<IcoGson>> setMainPageIco() {
        return RetrofitUtil.
                getInstance().
                getServerices().
                setMainPageIco();
    }
}
