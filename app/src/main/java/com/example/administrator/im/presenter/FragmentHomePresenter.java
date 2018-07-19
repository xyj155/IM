package com.example.administrator.im.presenter;

import com.example.administrator.im.contract.FragmentHomeContract;
import com.example.administrator.im.entity.BannerGson;
import com.example.administrator.im.entity.HomeData;
import com.example.administrator.im.gson.BaseGson;
import com.example.administrator.im.gson.IcoGson;
import com.example.administrator.im.model.FragmentHomeModel;
import com.example.administrator.im.util.ToastUtil;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/7/16.
 */

public class FragmentHomePresenter implements FragmentHomeContract.Presenter {
    private FragmentHomeContract.Model model = new FragmentHomeModel();
    private FragmentHomeContract.View mView;

    public FragmentHomePresenter(FragmentHomeContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getData() {
        mView.showMessage("数据获取中....");
        mView.showLoading();
        Observable.zip(model.setMainPageIco(), model.getBannerList(), new Func2<BaseGson<IcoGson>, BaseGson<List<BannerGson>>, HomeData>() {
            @Override
            public HomeData call(BaseGson<IcoGson> icoGsonBaseGson, BaseGson<List<BannerGson>> listBaseGson) {
                return new HomeData(icoGsonBaseGson, listBaseGson);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HomeData>() {
                    @Override
                    public void onCompleted() {
                        mView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideLoading();
                        ToastUtil.showToastWarning("请求出错:"+e.getMessage());
                    }

                    @Override
                    public void onNext(HomeData homeData) {
                        mView.hideLoading();
                        mView.setBannerURL(homeData.getOptimizationBean());
                        mView.setMainIco(homeData.getBannerBean());
                    }
                });

    }

    private static final String TAG = "FragmentHomePresenter";
}
