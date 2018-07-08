package com.example.administrator.im.ui.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.administrator.im.R;
import com.example.administrator.im.base.BaseActivity;
import com.example.administrator.im.ui.fragment.FragmentChatFriends;
import com.example.administrator.im.ui.fragment.FragmentCommunity;
import com.example.administrator.im.ui.fragment.FragmentHome;
import com.example.administrator.im.ui.fragment.FragmentMap;
import com.example.administrator.im.ui.fragment.FragmentUser;

public class HomeActivity extends BaseActivity {
    private RadioGroup rgMain;
    private FrameLayout main_frameLayout;
    private FragmentManager fragmentManager;
    private FragmentChatFriends fragmentChatFriends;
    private FragmentCommunity fragmentCommunity;
    private FragmentHome fragmentHome;
    private FragmentMap fragmentMap;
    private FragmentUser fragmentUser;

    @Override
    public int intiLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        main_frameLayout = (FrameLayout) findViewById(R.id.framlayout);
        rgMain = (RadioGroup) findViewById(R.id.rg_main);
        fragmentManager = getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        fragmentHome = new FragmentHome();
        transaction.add(R.id.framlayout, fragmentHome);
        transaction.commit();
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                hideAllFragment(transaction);
                switch (checkedId) {
                    case R.id.rbt_purse:
                        if(fragmentHome==null){
                            fragmentHome = new FragmentHome();
                            transaction.add(R.id.framlayout,fragmentHome);
                        }else{
                            transaction.show(fragmentHome);
                        }
                        break;
                    case R.id.rbt_friends:
                        if(fragmentCommunity==null){
                            fragmentCommunity = new FragmentCommunity();
                            transaction.add(R.id.framlayout,fragmentCommunity);
                        }else{
                            transaction.show(fragmentCommunity);
                        }
                        break;
                    case R.id.rbt_location:
                        if(fragmentMap==null){
                            fragmentMap = new FragmentMap();
                            transaction.add(R.id.framlayout,fragmentMap);
                        }else{
                            transaction.show(fragmentMap);
                        }
                        break;
                    case R.id.rbt_contact:
                        if(fragmentChatFriends==null){
                            fragmentChatFriends = new FragmentChatFriends();
                            transaction.add(R.id.framlayout,fragmentChatFriends);
                        }else{
                            transaction.show(fragmentChatFriends);
                        }
                        break;
                    case R.id.rbt_me:
                        if(fragmentUser==null){
                            fragmentUser = new FragmentUser();
                            transaction.add(R.id.framlayout,fragmentUser);
                        }else{
                            transaction.show(fragmentUser);
                        }
                        break;
                }
                transaction.commit();
            }
        });
    }

    @Override
    public void initData() {

    }
    //隐藏所有Fragment
    public void hideAllFragment(FragmentTransaction transaction){
        if(fragmentHome!=null){
            transaction.hide(fragmentHome);
        }
        if(fragmentCommunity!=null){
            transaction.hide(fragmentCommunity);
        }
        if(fragmentMap!=null){
            transaction.hide(fragmentMap);
        }
        if(fragmentChatFriends!=null){
            transaction.hide(fragmentChatFriends);
        }
        if(fragmentUser!=null){
            transaction.hide(fragmentUser);
        }
    }
}
