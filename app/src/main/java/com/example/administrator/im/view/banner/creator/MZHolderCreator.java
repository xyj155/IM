package com.example.administrator.im.view.banner.creator;

/**
 * Created by Administrator on 2018/6/13.
 */

public interface MZHolderCreator<VH extends MZViewHolder> {
    /**
     * 创建ViewHolder
     * @return
     */
    public VH createViewHolder();
}