package com.myapp.demomonth.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.myapp.demomonth.Model.User;
import com.myapp.demomonth.P.UserOperate;
import com.myapp.demomonth.R;

public class FriendActivity extends AppCompatActivity {

    private String friendAccount;

    private TextView tvAccount;
    private TextView tvUsername;
    private TextView tvPhone;
    private TextView tvCountry;
    private TextView tvAge;
    private TextView tvSex;

    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        friendAccount = getIntent().getStringExtra("friendAccount");

        find();
        init();

        btnBack.setOnClickListener(v -> finish());
    }

    private void find() {
        btnBack = findViewById(R.id.btnBackAF);
        tvAccount = findViewById(R.id.friend_account);
        tvUsername = findViewById(R.id.friend_username);
        tvPhone = findViewById(R.id.friend_phone);
        tvCountry = findViewById(R.id.friend_country);
        tvSex = findViewById(R.id.friend_sex);
        tvAge = findViewById(R.id.friend_age);
    }

    private void init() {
        User user = UserOperate.queryUser(this, friendAccount);
        tvSex.setText(user.getSex());
        tvAge.setText(user.getAge());
        tvCountry.setText(user.getCountry());
        tvAccount.setText(user.getAccount());
        tvUsername.setText(user.getUsername());
        tvPhone.setText(user.getPhone());
    }
}