package com.example.administrator.im.ui.fragment.login;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.im.R;
import com.example.administrator.im.base.BaseFragment;
import com.example.administrator.im.contract.RegisterFragmentContract;
import com.example.administrator.im.presenter.RegisterFragmentPresenter;
import com.example.administrator.im.util.ToastUtil;
import com.example.administrator.im.view.AppleDialog;
import com.example.administrator.im.view.CountDownTextView;

import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by Administrator on 2018/7/13/013.
 */

public class RegisterFragment extends BaseFragment implements  RegisterFragmentContract.View {
    private RegisterFragmentPresenter presenter = new RegisterFragmentPresenter(this);
    private Dialog dialog;

    public static final String patternPhone = "^[1][345789][0-9]{9}$";//验证手机号的正则
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            final Object data = msg.obj;
            Log.e("event", "event=" + event);
            if (result == SMSSDK.RESULT_COMPLETE) {
                System.out.println("----" + event);
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//提交验证码成功
//                        startActivity(new Intent(getContext(), VIPMainActivity.class));
                        presenter.Register(getActivity(), etRegisterTel.getText().toString(), etRegisterPasswordConfirm.getText().toString(), etRegisterTel.getText().toString());
                        ToastUtil.showToastSuccess("验证码验证成功");
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        ToastUtil.showToastSuccess("验证码已经发送");
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {//返回支持发送验证码的国家列表
                        Toast.makeText(getContext(), "获取国家列表成功", Toast.LENGTH_SHORT).show();
                    } else if (event == SMSSDK.RESULT_ERROR) {
                        ToastUtil.showToastWarning("错误！" + data);
                    }
                }
            }

        }
    };
    @InjectView(R.id.et_register_tel)
    EditText etRegisterTel;
    @InjectView(R.id.et_register_smscode)
    EditText etRegisterSmscode;
    @InjectView(R.id.tv_sendsmscode)
    CountDownTextView tvSendsmscode;
    @InjectView(R.id.et_register_password)
    EditText etRegisterPassword;
    @InjectView(R.id.et_register_password_confirm)
    EditText etRegisterPasswordConfirm;
    @InjectView(R.id.btn_register)
    Button btnRegister;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_register;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册回调监听，放到发送和验证前注册，注意这里是子线程需要传到主线程中去操作后续提示
        EventHandler eh = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };

        SMSSDK.registerEventHandler(eh);
    }

    @Override
    protected void setUpView(View view, Bundle bundle) {

    }

    @Override
    protected void setUpData() {

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


    @OnClick({R.id.tv_sendsmscode, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sendsmscode:
                final String tel = etRegisterTel.getText().toString();
                boolean matches = Pattern.matches(patternPhone, tel);
                if (matches) {
                    tvSendsmscode.setUsable(true);
                    tvSendsmscode.setCountDownMillis(60000);
                    tvSendsmscode.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SMSSDK.getVerificationCode("86", tel);
                        }
                    });
                    tvSendsmscode.start();
                } else {
                    tvSendsmscode.setUsable(false);
                    Toast.makeText(getActivity(), "请输入正确手机号码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_register:
                if (!etRegisterSmscode.getText().toString().isEmpty()) {
                    SMSSDK.submitVerificationCode("86", etRegisterTel.getText().toString(), etRegisterSmscode.getText().toString());
                } else {
                    Toast.makeText(getActivity(), "请输入验证码", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void showMessage(String message) {
        dialog = AppleDialog.createLoadingDialog(getActivity(), message);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
        dialog.dismiss();
    }
}
