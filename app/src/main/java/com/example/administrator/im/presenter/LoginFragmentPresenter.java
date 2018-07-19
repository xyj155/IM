package com.example.administrator.im.presenter;

import android.app.Activity;
import android.content.Intent;

import com.example.administrator.im.base.BaseObserver;
import com.example.administrator.im.contract.LoginFragmentContract;
import com.example.administrator.im.gson.BaseGson;
import com.example.administrator.im.gson.UserGson;
import com.example.administrator.im.model.LoginFragmentModel;
import com.example.administrator.im.ui.activity.HomeActivity;
import com.example.administrator.im.util.SPUtil;
import com.example.administrator.im.util.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/7/15.
 */

public class LoginFragmentPresenter implements LoginFragmentContract.Presenter {
    private final LoginFragmentContract.Model mModel = new LoginFragmentModel();

    private LoginFragmentContract.View mView;

    public LoginFragmentPresenter(LoginFragmentContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void login(final Activity context, final String username, String password) {
        mView.showLoading();
        mView.showMessage("正在登录");
        mModel.login(context, username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<UserGson>>() {
                    @Override
                    public void onError(String error) {
                        mView.hideLoading();
                        ToastUtil.showToastError("登录失败"+error);
                    }

                    @Override
                    public void onCompleted() {
                        mView.hideLoading();
                    }

                    @Override
                    public void onNext(BaseGson<UserGson> userGsonBaseGson) {
                        mView.hideLoading();
                        if (userGsonBaseGson.issuccess()) {
                            if (userGsonBaseGson.getCode()==200){
                                Map<String, Object> map = new HashMap<>();
                                map.put("username", username);
                                map.put("username", username);
                                map.put("login", true);
                                map.put("id", userGsonBaseGson.getData().getId());
                                SPUtil.saveUserInfor(map);
                                Intent intent = new Intent(context, HomeActivity.class);
                                context.startActivity(intent);
                                context.finish();
                                ToastUtil.showToastSuccess("登录成功");
                            }else {
                                ToastUtil.showToastError("登录失败"+userGsonBaseGson.getData().getMsg());
                            }
                        } else {
                            ToastUtil.showToastError("登录失败:"+userGsonBaseGson.getData().getMsg());
                        }
                    }
                });
    }
}
