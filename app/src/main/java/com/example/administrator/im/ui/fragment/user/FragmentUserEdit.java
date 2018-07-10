package com.example.administrator.im.ui.fragment.user;

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

public class FragmentUserEdit extends BaseFragment {

    private ViewPager vpUser;
    private PagerSlidingTabStrip ptUser;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_main_useredit;
    }

    @Override
    protected void setUpView(View view, Bundle bundle) {
        vpUser = view.findViewById(R.id.map_viewpager);
        ptUser = view.findViewById(R.id.map_pagerstrip);
        ptUser.setIndicatorColor(0xffffffff);
        ptUser.setUnderlineHeight(0);
        ptUser.setShouldExpand(true);
        vpUser.setOffscreenPageLimit(2);
        ptUser.setDividerPadding(20);
        ptUser.setTabPaddingLeftRight(100);
        ptUser.setScrollOffset(5);
        ptUser.setIndicatorColor(0xff0099ff);
        ptUser.setIndicatorHeight(10);
        ptUser.setIndicatorWeight(60);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentUserMyPhoto());
        fragments.add(new FragmentUserMyUsage());
        String title[] = {"我的照片", "动态观点"};
        PagerAdapter adapter = new PagerAdapter(getActivity().getSupportFragmentManager(), title, fragments);
        vpUser.setAdapter(adapter);
        ptUser.setViewPager(vpUser);
    }

    @Override
    protected void setUpData() {

    }
}
