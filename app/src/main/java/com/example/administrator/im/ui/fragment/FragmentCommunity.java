package com.example.administrator.im.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.administrator.im.R;
import com.example.administrator.im.base.BaseFragment;
import com.example.administrator.im.base.PagerAdapter;
import com.example.administrator.im.view.pagerstrip.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/9.
 */

public class FragmentCommunity extends BaseFragment {
    private ViewPager pgCommunity;
    private PagerSlidingTabStrip titlePageIndicator;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_main_community;
    }

    @Override
    protected void setUpView(View view, Bundle bundle) {
        pgCommunity = (ViewPager) view.findViewById(R.id.vp_community);
        titlePageIndicator = view.findViewById(R.id.tp_community);
        titlePageIndicator.setIndicatorColor(0xffffffff);
        titlePageIndicator.setUnderlineHeight(0);
        titlePageIndicator.setTabPaddingLeftRight(70);
        titlePageIndicator.setShouldExpand(true);
        titlePageIndicator.setScrollOffset(5);
        titlePageIndicator.setIndicatorColor(0xff0099ff);
        titlePageIndicator.setIndicatorHeight(12);
        titlePageIndicator.setIndicatorWeight(60);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentHome());
        fragments.add(new FragmentHome());
        fragments.add(new FragmentHome());
        fragments.add(new FragmentHome());
        fragments.add(new FragmentHome());
        String title[]={"推荐","专辑","感想","纪录","开心"};
        PagerAdapter adapter = new PagerAdapter(getActivity().getSupportFragmentManager(),title,fragments);
        pgCommunity.setAdapter(adapter);
        titlePageIndicator.setViewPager(pgCommunity);
    }




    @Override
    protected void setUpData() {

    }


}
