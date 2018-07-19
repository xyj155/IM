package com.example.administrator.im.contract;

import com.example.administrator.im.gson.Active;
import com.example.administrator.im.gson.BaseGson;
import com.example.administrator.im.gson.UserGson;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2018/7/15.
 */

public interface FragmentMapContract {
    interface Model {
        Observable<BaseGson<List<UserGson<Active>>>> queryActive(String location);
    }

    interface View {
        void showMessage(String message);

        void showLoading();

        void hideLoading();
       void queryLocation(List<UserGson<Active>> data);
    }

    interface Presenter {
        void Location(String location);
    }
}
