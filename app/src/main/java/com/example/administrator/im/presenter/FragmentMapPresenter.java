package com.example.administrator.im.presenter;

import android.util.Log;

import com.example.administrator.im.base.BaseObserver;
import com.example.administrator.im.contract.FragmentMapContract;
import com.example.administrator.im.gson.Active;
import com.example.administrator.im.gson.BaseGson;
import com.example.administrator.im.gson.UserGson;
import com.example.administrator.im.model.FragmentMapModel;
import com.example.administrator.im.util.ToastUtil;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/7/15.
 */

public class FragmentMapPresenter implements FragmentMapContract.Presenter {
    private FragmentMapContract.Model model = new FragmentMapModel();
    private FragmentMapContract.View mView;

    public FragmentMapPresenter(FragmentMapContract.View view) {
        this.mView = view;
    }

    private static final String TAG = "FragmentMapPresenter";

    @Override
    public void Location(String location) {
        mView.showLoading();
        mView.showMessage("正在获取数据...");
        model.queryActive(location)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<List<UserGson<Active>>>>() {
                    @Override
                    public void onError(String error) {
                        ToastUtil.showToastWarning("错误"+error);
                        Log.i(TAG, "onError: "+error);
                        mView.hideLoading();
                    }

                    @Override
                    public void onCompleted() {
                        mView.hideLoading();
                    }

                    @Override
                    public void onNext(BaseGson<List<UserGson<Active>>> listBaseGson) {
                        mView.hideLoading();
                        Log.i(TAG, "onNext: "+listBaseGson.getCode());
                        if (listBaseGson.getCode() == 200) {
                            mView.queryLocation(listBaseGson.getData());
                            mView.hideLoading();
                        } else {
                            mView.hideLoading();
                            mView.queryLocation(listBaseGson.getData());
                            ToastUtil.showToastWarning("附近没有人啊");
                        }
                    }
                });
    }
}
