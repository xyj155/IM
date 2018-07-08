package com.example.administrator.im.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.administrator.im.R;
import com.example.administrator.im.base.BaseFragment;
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
    protected void setUpView(View view) {
        pgCommunity = (ViewPager) view.findViewById(R.id.vp_community);
        titlePageIndicator = view.findViewById(R.id.tp_community);
        titlePageIndicator.setIndicatorColor(0xffffffff);
        titlePageIndicator.setUnderlineHeight(0);
        titlePageIndicator.setDividerColor(0xffffffff);
        titlePageIndicator.setShouldExpand(true);
        titlePageIndicator.setScrollOffset(5);
        titlePageIndicator.setIndicatorColor(0xff0099ff);
        titlePageIndicator.setIndicatorHeight(17);
        titlePageIndicator.setIndicatorWeight(60);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentHome());
        fragments.add(new FragmentHome());
        fragments.add(new FragmentHome());
        fragments.add(new FragmentHome());
        fragments.add(new FragmentHome());
        String title[]={"推荐","专辑","感想","纪录","短视频"};
        PagerAdapter adapter = new PagerAdapter(getActivity().getSupportFragmentManager(),title,fragments);
        pgCommunity.setAdapter(adapter);
        titlePageIndicator.setViewPager(pgCommunity);
    }


    @Override
    protected void setUpData() {

    }

    private class PagerAdapter extends FragmentPagerAdapter {
        private String title[] = {};
        private List<Fragment> fragments;

        public PagerAdapter(FragmentManager fm, String[] title, List<Fragment> fragments) {
            super(fm);
            this.title = title;
            this.fragments = fragments;
        }

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return title.length;
        }
    }
}
