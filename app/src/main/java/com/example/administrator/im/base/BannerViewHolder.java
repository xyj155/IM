package com.example.administrator.im.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.im.R;
import com.example.administrator.im.view.banner.creator.MZViewHolder;

/**
 * Created by Administrator on 2018/7/16.
 */

public  class BannerViewHolder implements MZViewHolder<String > {
    private ImageView mImageView;
    @Override
    public View createView(Context context) {
        // 返回页面布局
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
        mImageView = (ImageView) view.findViewById(R.id.banner_image);
        return view;
    }

    @Override
    public void onBind(Context context, int position, String data) {
        // 数据绑定
        Glide.with(context).load(data).asBitmap().into(mImageView);
    }
}