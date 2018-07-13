package com.example.administrator.im.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;

import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.im.R;
import com.example.administrator.im.adapter.ChatAdapter;
import com.example.administrator.im.base.BaseActivity;
import com.example.administrator.im.entity.ChatEntity;
import com.example.administrator.im.util.IMUtils;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.api.BasicCallback;

public class ContactActivity extends AppCompatActivity {
    private RecyclerView ry_contact;
    private ImageView btn_send;
    private EditText et_content;
    private BaseMultiItemQuickAdapter<ChatEntity, BaseViewHolder> mChatAdapter;
    private List<Message> messages = new ArrayList<>();
    private List<ChatEntity> data=new ArrayList<>();
    private Conversation mConversation;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        initView();
        initData();
    }


    public void initView() {
        ry_contact = (RecyclerView) findViewById(R.id.ry_contact);
        ry_contact.setLayoutManager(new LinearLayoutManager(ContactActivity.this));
        btn_send = (ImageView) findViewById(R.id.btn_send);
        et_content = (EditText) findViewById(R.id.et_content);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setSubtitle("和徐易杰对话中.....");
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }



    public void initData() {
        IMUtils.login("123456", "123456", new BasicCallback() {
            @Override
            public void gotResult(int i, final String s) {
                if (i == 0) {
                    mConversation = Conversation.createSingleConversation("456789");
                    btn_send.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String content = et_content.getText().toString();
                            Message message1 = IMUtils.sendTextMessage(mConversation, content);
                            et_content.setText("");
                            messages.add(message1);
                            for (Message message : messages) {
                                if (message.getFromName().equals("123456")) {
                                        data.add(ChatEntity.client(message));
                                } else {
                                    data.add(ChatEntity.service(message));
                                }
                            }
                            mChatAdapter = new ChatAdapter(data);
                            ry_contact.setAdapter(mChatAdapter);
                        }
                    });
                }
            }
        });
    }
}
