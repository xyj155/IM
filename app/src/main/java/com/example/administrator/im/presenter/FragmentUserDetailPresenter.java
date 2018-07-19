package com.example.administrator.im.presenter;

import com.example.administrator.im.base.BaseObserver;
import com.example.administrator.im.contract.FragmentUserDetailContract;
import com.example.administrator.im.model.FragmentUserDetailModel;
import com.example.administrator.im.util.ToastUtil;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/7/17.
 */

public class FragmentUserDetailPresenter implements FragmentUserDetailContract.Presenter {
    private FragmentUserDetailContract.Model model = new FragmentUserDetailModel();
    private FragmentUserDetailContract.View view;

    public FragmentUserDetailPresenter(FragmentUserDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void setUserHeadImg(String imgurl, int userid) {
        File file = new File(imgurl);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        model.setUserHeadImg(part, userid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ResponseBody>() {
                    @Override
                    public void onError(String error) {
                        ToastUtil.showToastWarning("头像更换错误"+error);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(ResponseBody stringBaseGson) {
                        ToastUtil.showToastSuccess("头像更换成功");
                    }
                });
    }
}
