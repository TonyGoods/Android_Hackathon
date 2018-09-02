package com.myapp.demomonth.View.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.myapp.demomonth.P.InitChat;
import com.myapp.demomonth.P.MessageOperate;
import com.myapp.demomonth.P.SendMessage;
import com.myapp.demomonth.R;

import java.util.Objects;

public class ChatActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TextView tvFriendName;
    private TextView tvFriendInfo;
    private String account;
    private String friendAccount;
    private EditText write;
    private ScrollView scroll;
    private LinearLayout messageLayout;
    private LinearLayout touchLayout;
    private Button btnSend;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        account = getIntent().getStringExtra("account");
        friendAccount = getIntent().getStringExtra("friendAccount");
        find();
        init();

        touchLayout.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            boolean flag;
            flag = Objects.requireNonNull(getCurrentFocus()).getId() == R.id.writeChat;
            scroll.fullScroll(View.FOCUS_DOWN);
            if (flag) {
                write.requestFocus();
            }
        });

        btnBack.setOnClickListener(v -> finish());
        btnSend.setOnClickListener(v -> {
            String message = write.getText().toString();
            if (!message.isEmpty()) {
                SendMessage.send(this, messageLayout, message);
                MessageOperate.sendMessage(this, account, friendAccount, message);
                write.setText("");
            }
        });

        tvFriendInfo.setOnClickListener(v -> {
            Intent intent = new Intent(this, FriendActivity.class);
            intent.putExtra("friendAccount", friendAccount);
            startActivity(intent);
        });
    }

    private void find() {
        btnBack = findViewById(R.id.btnBackChat);
        btnSend = findViewById(R.id.btnSendChat);
        tvFriendName = findViewById(R.id.friendNameChat);
        tvFriendInfo = findViewById(R.id.friendInfoChat);
        scroll = findViewById(R.id.scrollChat);
        write = findViewById(R.id.writeChat);
        touchLayout = findViewById(R.id.chat_touch_layout);
        messageLayout = findViewById(R.id.chat_message_layout);
    }

    private void init() {
        tvFriendName.setText(getIntent().getStringExtra("friendName"));
        InitChat.init(this, messageLayout, account, friendAccount);
    }
}