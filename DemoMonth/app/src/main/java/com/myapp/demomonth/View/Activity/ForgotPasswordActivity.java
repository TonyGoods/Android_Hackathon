package com.myapp.demomonth.View.Activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myapp.demomonth.P.AccountOperate;
import com.myapp.demomonth.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private Button button;
    private EditText account;
    private TextView tvPassword;
    private Button btnBack;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_view);
        find();
        button.setOnClickListener(v -> {
            String password = AccountOperate.gotPassword(this, account.getText().toString());
            if (password == null) {
                tvPassword.setText("无此账号！");
            } else {
                tvPassword.setText("密码是：" + password);
            }
            linearLayout.setVisibility(View.VISIBLE);
        });

        btnBack.setOnClickListener(v -> finish());
    }

    private void find() {
        linearLayout = findViewById(R.id.linearLayout);
        button = findViewById(R.id.btnFPsw);
        account = findViewById(R.id.usernameFPsw);
        tvPassword = findViewById(R.id.passwordFPsw);
        btnBack = findViewById(R.id.btnBackFPsw);
    }
}