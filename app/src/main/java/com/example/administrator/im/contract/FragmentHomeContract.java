package com.example.administrator.im.contract;

import com.example.administrator.im.entity.BannerGson;
import com.example.administrator.im.gson.BaseGson;
import com.example.administrator.im.gson.IcoGson;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2018/7/16.
 */

public interface FragmentHomeContract {
    interface Model {
        Observable<BaseGson<List<BannerGson>>> getBannerList();

        Observable<BaseGson<IcoGson>> setMainPageIco();
    }

    interface View {
        void showMessage(String message);

        void showLoading();

        void setBannerURL(List<BannerGson> bannerURL);
        void setMainIco(IcoGson ico);

        void hideLoading();
    }

    interface Presenter {
        void getData();
    }
}
