package com.example.administrator.im.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import cn.jpush.im.android.api.model.Message;

/**
 * Created by Administrator on 2018/7/11.
 */

public class ChatEntity implements MultiItemEntity {

    public static final int TYPE_SERVICES_MESSAGE = 1;

    public static final int TYPE_CLIENT_MESSAGE = 2;

    private int itemType;

    private Message data;

    public ChatEntity(int itemType, Message data) {
        this.itemType = itemType;
        this.data = data;
    }

    /**
     * @param data
     * @return
     */
    public static ChatEntity service(Message data) {
        return new ChatEntity(TYPE_SERVICES_MESSAGE, data);
    }

    /**
     * @param data
     * @return
     */
    public static ChatEntity client(Message data) {
        return new ChatEntity(TYPE_CLIENT_MESSAGE, data);
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public Message getData() {
        return data;
    }

    public void setData(Message data) {
        this.data = data;
    }
}