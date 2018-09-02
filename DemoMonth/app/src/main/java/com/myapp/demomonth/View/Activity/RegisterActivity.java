package com.myapp.demomonth.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.myapp.demomonth.P.AccountOperate;
import com.myapp.demomonth.R;

public class RegisterActivity extends AppCompatActivity {
    private EditText account;
    private EditText password;
    private Button btnRegister;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);
        find();

        btnRegister.setOnClickListener(v -> {
            String usernameStr = account.getText().toString();
            String passwordStr = password.getText().toString();
            if (usernameStr.isEmpty() || passwordStr.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "请将注册信息填写完整！", Toast.LENGTH_LONG).show();
            }
            if (AccountOperate.register(this, usernameStr, passwordStr)) {
                Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegisterActivity.this, "注册失败！该账号已存在！", Toast.LENGTH_SHORT).show();
            }
        });

        btnBack.setOnClickListener(v -> finish());
    }

    private void find() {
        btnBack = findViewById(R.id.btnBackReg);
        btnRegister = findViewById(R.id.btnRegister);
        account = findViewById(R.id.usernameReg);
        password = findViewById(R.id.passwordReg);
    }
}