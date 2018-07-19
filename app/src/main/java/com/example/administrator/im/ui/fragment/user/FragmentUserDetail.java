package com.example.administrator.im.ui.fragment.user;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.im.R;
import com.example.administrator.im.base.BaseFragment;
import com.example.administrator.im.contract.FragmentUserDetailContract;
import com.example.administrator.im.pictureSelector.ISNav;
import com.example.administrator.im.pictureSelector.common.ImageLoader;
import com.example.administrator.im.pictureSelector.config.ISListConfig;
import com.example.administrator.im.presenter.FragmentUserDetailPresenter;
import com.example.administrator.im.util.SPUtil;
import com.example.administrator.im.view.CircleImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static com.amap.api.mapcore.util.ae.o;
import static com.example.administrator.im.R.id.map;

/**
 * Created by Administrator on 2018/7/9.
 */

public class FragmentUserDetail extends BaseFragment implements FragmentUserDetailContract.View {

    private static final int REQUEST_LIST_CODE = 0;
    private static final int REQUEST_CAMERA_CODE = 1;
    @InjectView(R.id.tv_userinfor)
    TextView tvUserinfor;
    @InjectView(R.id.tv_user_location)
    TextView tvUserLocation;
    @InjectView(R.id.tv_post)
    TextView tvPost;
    @InjectView(R.id.tv_tag)
    TextView tvTag;
    @InjectView(R.id.tv_notification)
    TextView tvNotification;
    @InjectView(R.id.tv_setting)
    TextView tvSetting;
    @InjectView(R.id.img_user_head)
    CircleImageView imgUserHead;
    private FragmentUserDetailPresenter presenter = new FragmentUserDetailPresenter(this);

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_main_userdetail;
    }

    @Override
    protected void setUpView(View view, Bundle bundle) {
    }

    @Override
    protected void setUpData() {

        if (SPUtil.getSPValue("userhead") != null) {
            Glide.with(getActivity()).load(SPUtil.getSPValue("userhead")).asBitmap().into(imgUserHead);
        }
        ISNav.getInstance().init(new ImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
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


    @OnClick({R.id.tv_userinfor, R.id.tv_user_location, R.id.tv_post, R.id.tv_tag, R.id.tv_notification, R.id.tv_setting, R.id.img_user_head})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_userinfor:
                break;
            case R.id.tv_user_location:
                break;
            case R.id.tv_post:
                break;
            case R.id.tv_tag:
                break;
            case R.id.tv_notification:
                break;
            case R.id.tv_setting:
                break;
            case R.id.img_user_head:
                // 自由配置选项
                ISListConfig config = new ISListConfig.Builder()
                        // 是否多选, 默认true
                        .multiSelect(false)
                        // 是否记住上次选中记录, 仅当multiSelect为true的时候配置，默认为true
                        .rememberSelected(false)
                        // “确定”按钮背景色
                        .btnBgColor(Color.GRAY)
                        // “确定”按钮文字颜色
                        .btnTextColor(Color.BLUE)
                        // 返回图标ResId
                        .backResId(R.mipmap.ic_back)
                        // 标题
                        .needCamera(true)
                        .titleBgColor(0xffffffff)
                        .title("选择图片")
                        // 标题文字颜色
                        .titleColor(Color.BLACK)
                        .cropSize(1, 1, 200, 200)
                        .needCrop(true)
                        .needCamera(false)
                        .maxNum(1)
                        .build();
                // 跳转到图片选择器
                ISNav.getInstance().toListActivity(this, config, REQUEST_LIST_CODE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Map<String, String> map = new HashMap<>();
        if (requestCode == REQUEST_LIST_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra("result");
            System.out.println(pathList);
            Glide.with(getActivity()).load(pathList.get(0)).asBitmap().into(imgUserHead);
            map.put("userhead", pathList.get(0));
            presenter.setUserHeadImg(pathList.get(0), Integer.valueOf(SPUtil.getSPValue("id").toString()));
        } else if (requestCode == REQUEST_CAMERA_CODE && resultCode == RESULT_OK && data != null) {
            String path = data.getStringExtra("result");
            presenter.setUserHeadImg(path, Integer.valueOf(SPUtil.getSPValue("id").toString()));
            map.put("userhead", path);
            Glide.with(getActivity()).load(path).asBitmap().into(imgUserHead);
        }
        SPUtil.saveUserInfor(map);
    }


}
