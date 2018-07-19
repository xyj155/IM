package com.example.administrator.im.ui.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.im.R;
import com.example.administrator.im.base.BannerViewHolder;
import com.example.administrator.im.base.BaseFragment;
import com.example.administrator.im.contract.FragmentHomeContract;
import com.example.administrator.im.entity.BannerGson;
import com.example.administrator.im.gson.IcoGson;
import com.example.administrator.im.presenter.FragmentHomePresenter;
import com.example.administrator.im.view.AppleDialog;
import com.example.administrator.im.view.banner.MZBannerView;
import com.example.administrator.im.view.banner.creator.MZHolderCreator;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/9.
 */

public class FragmentHome extends BaseFragment implements FragmentHomeContract.View {
    @InjectView(R.id.pic1)
    ImageView pic1;
    @InjectView(R.id.pic2)
    ImageView pic2;
    @InjectView(R.id.pic3)
    ImageView pic3;
    @InjectView(R.id.pic4)
    ImageView pic4;
    private Toolbar toolbar;
    private NestedScrollView scrollView;
    private MZBannerView banner_main;
    private FragmentHomePresenter presenter = new FragmentHomePresenter(this);
    private Dialog dialog;
    private BannerViewHolder bannerViewHolder;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_main_home;
    }

    @Override
    protected void setUpView(View view, Bundle bundle) {
        toolbar = view.findViewById(R.id.toolbar);

        scrollView = view.findViewById(R.id.scrollView);
        banner_main = view.findViewById(R.id.banner_main);
    }

    @Override
    protected void setUpData() {
        presenter.getData();
        banner_main.setDelayedTime(5000);
        banner_main.setDuration(1000);
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY <= 0) {  //设置标题的背景颜色
                    toolbar.setBackgroundColor(Color.argb((int) 0, 144, 151, 166));
                    System.out.println("0");
                    toolbar.setTitle("");
                } else if (scrollY > 0 && scrollY <= 450) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
                    float scale = (float) scrollY / 450;
                    float alpha = (255 * scale);
                    System.out.println("1");
                    toolbar.setTitle("首页");
//                    toolbar.setTextColor(Color.argb((int) alpha, 255,255,255));
                    toolbar.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                } else {  //滑动到banner下面设置普通颜色
                    System.out.println("2");
                    toolbar.setTitle("首页");
                    toolbar.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
                }
            }
        });
    }

    @Override
    public void showMessage(String message) {
        dialog = AppleDialog.createLoadingDialog(getActivity(), message);
    }

    @Override
    public void showLoading() {
        dialog.show();
    }

    @Override
    public void setBannerURL(List<BannerGson> bannerURL) {
        bannerViewHolder = new BannerViewHolder();
        // 设置数据

        List<String> list = new ArrayList<>();
        for (int i = 0; i < bannerURL.size(); i++) {
            list.add(bannerURL.get(i).getBanner());
        }
        banner_main.setPages(list, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return bannerViewHolder;
            }
        });
    }

    @Override
    public void setMainIco(IcoGson ico) {
        Glide.with(getActivity()).load(ico.getIco1()).into(pic1);
        Glide.with(getActivity()).load(ico.getIco2()).into(pic2);
        Glide.with(getActivity()).load(ico.getIco3()).into(pic3);
        Glide.with(getActivity()).load(ico.getIco4()).into(pic4);
    }

    @Override
    public void onPause() {
        super.onPause();
        banner_main.pause();//暂停轮播
    }

    @Override
    public void onResume() {
        super.onResume();
        banner_main.start();//开始轮播
    }

    @Override
    public void hideLoading() {
        dialog.dismiss();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.pic1, R.id.pic2, R.id.pic3, R.id.pic4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pic1:
                break;
            case R.id.pic2:
                break;
            case R.id.pic3:
                break;
            case R.id.pic4:
                break;
        }
    }
}
