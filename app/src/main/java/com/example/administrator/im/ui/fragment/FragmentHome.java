package com.example.administrator.im.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.im.R;
import com.example.administrator.im.base.BaseFragment;

/**
 * Created by Administrator on 2018/7/9.
 */

public class FragmentHome extends BaseFragment {
    private Toolbar toolbar;
private NestedScrollView scrollView;
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_main_home;
    }

    @Override
    protected void setUpView(View view, Bundle bundle) {
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle("首页");
        scrollView=view.findViewById(R.id.scrollView);
    }

    @Override
    protected void setUpData() {
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
}
