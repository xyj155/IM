package com.example.administrator.im.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.im.R;
import com.example.administrator.im.ui.activity.ContactActivity;
import com.example.administrator.im.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.im.R.id.toolbar;

/**
 * Created by Administrator on 2018/7/9.
 */

public class FragmentChatFriends extends Fragment {
    private RecyclerView ry_chat;
private CircleImageView img_user;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_main_chatfriends, container, false);
        ry_chat = inflate.findViewById(R.id.ry_contact);
        img_user=inflate.findViewById(R.id.img_user);
        ry_chat.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<User> list = new ArrayList<>();
        list.add(new User("架构师", "我们不是男孩子", "http://img3.duitang.com/uploads/item/201603/17/20160317194638_xjJLf.jpeg"));
        list.add(new User("程序员", "我是gay", "http://img5.duitang.com/uploads/item/201508/20/20150820104208_AzFPV.jpeg"));
        list.add(new User("JAVA", "上班好累", "http://img4.duitang.com/uploads/item/201408/07/20140807162146_VvPvP.jpeg"));
        list.add(new User("Android", "为什么如此", "http://img.zcool.cn/community/018f27563877d632f87512f63868ff.jpg"));
        list.add(new User("PHP", "还行....", "http://img.zcool.cn/community/01d65d563877d832f87512f672b15a.jpg@2o.jpg"));
        list.add(new User("Python", "不是人工智能，我是垃圾", "http://img.zcool.cn/community/011caa5548b0520000019ae9ba7140.jpg"));
        ChatAdapter adapter = new ChatAdapter(list);

        Toolbar toolbar = inflate.findViewById(R.id.toolbar);
        toolbar.setTitle("联系人");
        ry_chat.setAdapter(adapter);
        return inflate;
    }

    private class ChatAdapter extends BaseQuickAdapter<User, BaseViewHolder> {

        public ChatAdapter(List<User> data) {
            super(R.layout.ry_chatlist_item, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final User item) {
            ImageView logoview = helper.getView(R.id.img_head);
            helper.setText(R.id.tv_username, item.getUsernanme())
                    .setText(R.id.tv_style, item.getStyle())
            .setOnClickListener(R.id.ll_contact, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getContext(),ContactActivity.class);
                    intent.putExtra("name",item.getUsernanme());
                    intent.putExtra("userhead",item.getUserhead());
                    startActivity(intent);
                }
            });
            Glide.with(getActivity()).load(item.getUserhead()).asBitmap().into(logoview);
        }
    }

    private class User {
        private String usernanme;
        private String style;
        private String userhead;

        public User(String usernanme, String style, String userhead) {
            this.usernanme = usernanme;
            this.style = style;
            this.userhead = userhead;
        }

        public String getUsernanme() {
            return usernanme;
        }

        public void setUsernanme(String usernanme) {
            this.usernanme = usernanme;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getUserhead() {
            return userhead;
        }

        public void setUserhead(String userhead) {
            this.userhead = userhead;
        }
    }
}
