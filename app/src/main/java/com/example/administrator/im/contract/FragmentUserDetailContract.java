package com.example.administrator.im.contract;


import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by Administrator on 2018/7/17.
 */

public interface FragmentUserDetailContract {
    interface Model {
        Observable<ResponseBody> setUserHeadImg(MultipartBody.Part file, int id);
    }

    interface View {
    }

    interface Presenter {
        void setUserHeadImg(String imgurl, int userid);
    }
}
