package com.myapp.demomonth.View.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.myapp.demomonth.P.AccountOperate;
import com.myapp.demomonth.P.HasLoginOperate;
import com.myapp.demomonth.R;

public class LoginActivity extends Activity {

    private TextView register;
    private TextView forgotPassword;
    private EditText account;
    private EditText password;
    private Button btnLogin;
    private ToggleButton showPsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String usernameStr;
        if ((usernameStr = HasLoginOperate.query(this)) != null) {
            startMainActivity(usernameStr);
        }
        find();

        btnLogin.setOnClickListener(v -> {
            if (AccountOperate.login(this, account.getText().toString(), password.getText().toString())) {
                startMainActivity(account.getText().toString());
            } else {
                Toast.makeText(this, "登录失败，账号或密码错误！", Toast.LENGTH_SHORT).show();
            }
        });

        register.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        showPsw.setOnCheckedChangeListener((v, isChecked) -> {
            if (isChecked) {
                showPsw.setBackground(getDrawable(R.mipmap.psw_show));
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                password.setSelection(password.getText().toString().length());
            } else {
                showPsw.setBackground(getDrawable(R.mipmap.psw_close));
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                password.setSelection(password.getText().toString().length());
            }
        });

        forgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(this, ForgotPasswordActivity.class);
            startActivity(intent);
        });
    }

    private void find() {
        showPsw = findViewById(R.id.showPwd);
        register = findViewById(R.id.register);
        btnLogin = findViewById(R.id.btnLogin);
        account = findViewById(R.id.username);
        password = findViewById(R.id.password);
        forgotPassword = findViewById(R.id.forgotPassword);
    }

    private void startMainActivity(String username) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("account", username);
        startActivity(intent);
        finish();
    }
}