package com.example.administrator.im.model;

import com.example.administrator.im.contract.FragmentUserDetailContract;
import com.example.administrator.im.util.RetrofitUtil;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by Administrator on 2018/7/17.
 */

public class FragmentUserDetailModel implements FragmentUserDetailContract.Model {
    @Override
    public Observable<ResponseBody> setUserHeadImg(MultipartBody.Part file, int id) {
        return RetrofitUtil.getInstance().getServerices().setUserHead(id,file);
    }
}
