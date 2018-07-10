package com.example.administrator.im.ui.fragment.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.im.R;
import com.example.administrator.im.base.BaseFragment;
import com.example.administrator.im.base.PagerAdapter;
import com.example.administrator.im.view.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/9.
 */

public class FragmentUser extends BaseFragment {

    private VerticalViewPager vpUser;
    private PagerAdapter adapter;
    private Toolbar toolbar;
    private static final float MIN_SCALE = 0.75f;
    private static final float MIN_ALPHA = 0.75f;
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_main_user;
    }

    @Override
    protected void setUpView(View view, Bundle bundle) {
        vpUser = view.findViewById(R.id.vp_user);
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setSubtitle("更多");
    }

    @Override
    protected void setUpData() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentUserDetail());
        fragments.add(new FragmentUserEdit());
        String arr[]={"",""};
        adapter = new PagerAdapter(getActivity().getSupportFragmentManager(),arr, fragments);
        vpUser.setAdapter(adapter);
        vpUser.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View view, float position) {
                int pageWidth = view.getWidth();
                int pageHeight = view.getHeight();

                if (position < -1) { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    view.setAlpha(0);

                } else if (position <= 1) { // [-1,1]
                    // Modify the default slide transition to shrink the page as well
                    float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                    float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                    float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                    if (position < 0) {
                        view.setTranslationY(vertMargin - horzMargin / 2);
                    } else {
                        view.setTranslationY(-vertMargin + horzMargin / 2);
                    }

                    // Scale the page down (between MIN_SCALE and 1)
                    view.setScaleX(scaleFactor);
                    view.setScaleY(scaleFactor);

                    // Fade the page relative to its size.
                    view.setAlpha(MIN_ALPHA +
                            (scaleFactor - MIN_SCALE) /
                                    (1 - MIN_SCALE) * (1 - MIN_ALPHA));

                } else { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    view.setAlpha(0);
                }
            }
        });
    }
}
