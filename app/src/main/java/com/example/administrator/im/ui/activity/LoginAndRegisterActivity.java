package com.example.administrator.im.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.im.R;
import com.example.administrator.im.base.BaseActivity;
import com.example.administrator.im.ui.fragment.FragmentCommunity;
import com.example.administrator.im.ui.fragment.FragmentHome;
import com.example.administrator.im.ui.fragment.login.LoginFragment;
import com.example.administrator.im.ui.fragment.login.RegisterFragment;

public class LoginAndRegisterActivity extends BaseActivity {

    private RadioGroup rg_login;
    private RadioButton rb_login, rb_register;
    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;
    private FrameLayout main_login;
    private FragmentManager fragmentManager;

    @Override
    public int intiLayout() {
        return R.layout.activity_login_and_register;
    }

    @Override
    public void initView() {
        main_login = (FrameLayout) findViewById(R.id.fl_login);
        rg_login = (RadioGroup) findViewById(R.id.rg_login);
        rb_login = (RadioButton) findViewById(R.id.rb_login);
        rb_register = (RadioButton) findViewById(R.id.rb_register);
        fragmentManager = getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        loginFragment = new LoginFragment();
        transaction.add(R.id.fl_login, loginFragment);
        transaction.commit();
        rg_login.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                hideAllFragment(transaction);
                switch (i) {
                    case R.id.rb_login:
                        if (loginFragment == null) {
                            loginFragment = new LoginFragment();
                            transaction.add(R.id.fl_login, loginFragment);
                        } else {
                            transaction.show(loginFragment);
                        }
                        break;
                    case R.id.rb_register:
                        if (registerFragment == null) {
                            registerFragment = new RegisterFragment();
                            transaction.add(R.id.fl_login, registerFragment);
                        } else {
                            transaction.show(registerFragment);
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
    public void hideAllFragment(FragmentTransaction transaction) {
        if (loginFragment != null) {
            transaction.hide(loginFragment);
        }
        if (registerFragment != null) {
            transaction.hide(registerFragment);
        }
    }
}
