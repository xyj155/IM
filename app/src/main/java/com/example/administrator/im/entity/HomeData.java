package com.example.administrator.im.entity;

import com.example.administrator.im.gson.BaseGson;
import com.example.administrator.im.gson.IcoGson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/16.
 */

public class HomeData {
    private IcoGson icoGsons;

    private List<BannerGson> bannerGsons;

    public HomeData(BaseGson<IcoGson> bannerData, BaseGson<List<BannerGson>> optimizationData) {
        icoGsons = bannerData.getData();
        bannerGsons = optimizationData.getData();
    }

    public IcoGson getBannerBean() {
        if (icoGsons == null) {
            return new IcoGson();
        }
        return icoGsons;
    }

    public List<BannerGson> getOptimizationBean() {
        if (bannerGsons == null) {
            return new ArrayList<>();
        }
        return bannerGsons;
    }
}
