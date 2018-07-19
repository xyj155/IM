package com.example.administrator.im.ui.fragment.login;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.im.R;
import com.example.administrator.im.base.BaseFragment;
import com.example.administrator.im.contract.LoginFragmentContract;
import com.example.administrator.im.presenter.LoginFragmentPresenter;
import com.example.administrator.im.view.AppleDialog;

/**
 * Created by Administrator on 2018/7/13/013.
 */

public class LoginFragment extends BaseFragment implements View.OnClickListener, LoginFragmentContract.View {
    private EditText etLoginTel, etLoginPassword;
    private Button btnLogin;
    private LoginFragmentPresenter presenter = new LoginFragmentPresenter(this);
    private Dialog dialog;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_login;
    }

    @Override
    protected void setUpView(View view, Bundle bundle) {
        etLoginTel = view.findViewById(R.id.et_login_tel);
        etLoginPassword = view.findViewById(R.id.et_login_password);
        btnLogin = view.findViewById(R.id.btn_login_login);
    }

    @Override
    protected void setUpData() {
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_login:
                presenter.login(getActivity(),etLoginTel.getText().toString(), etLoginPassword.getText().toString());
                break;
        }
    }

    @Override
    public void showMessage(String message) {
        dialog = AppleDialog.createLoadingDialog(getActivity(),message);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
        dialog.dismiss();
    }
}
