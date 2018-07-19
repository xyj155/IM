package com.example.administrator.im.presenter;

import android.app.Activity;
import android.content.Intent;

import com.example.administrator.im.base.BaseObserver;
import com.example.administrator.im.contract.RegisterFragmentContract;
import com.example.administrator.im.gson.BaseGson;
import com.example.administrator.im.gson.UserGson;
import com.example.administrator.im.model.RegisterFragmentModel;
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

public class RegisterFragmentPresenter implements RegisterFragmentContract.Presenter {
    private RegisterFragmentContract.Model mModel = new RegisterFragmentModel();

    public RegisterFragmentPresenter(RegisterFragmentContract.View view) {
        this.mView = view;
    }

    private RegisterFragmentContract.View mView;

    @Override
    public void Register(final Activity context, final String username, final String password, final String tel) {
        mView.showLoading();
        mView.showMessage("正在登录");
        mModel.register(username, password,tel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<UserGson>>() {
                    @Override
                    public void onError(String error) {
                        mView.hideLoading();
                        ToastUtil.showToastError("注册失败"+error);
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
                                map.put("password", password);
                                map.put("tel", tel);
                                map.put("login", true);
                                map.put("id", userGsonBaseGson.getData().getId()
                                );
                                SPUtil.saveUserInfor(map);
                                Intent intent = new Intent(context, HomeActivity.class);
                                context.startActivity(intent);
                                context.finish();
                                ToastUtil.showToastSuccess("注册成功");
                            }else {
                                ToastUtil.showToastError("注册失败"+userGsonBaseGson.getData().getMsg());
                            }
                        } else {
                            ToastUtil.showToastError("注册失败:"+userGsonBaseGson.getData().getMsg());
                        }
                    }
                });
    }
}
