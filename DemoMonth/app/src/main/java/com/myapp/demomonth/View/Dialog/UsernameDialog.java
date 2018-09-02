package com.myapp.demomonth.View.Dialog;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.myapp.demomonth.P.UserOperate;
import com.myapp.demomonth.R;
import com.myapp.demomonth.View.Fragment.IFragment;

import java.util.Objects;

public class UsernameDialog extends AppCompatDialog {

    private Button button;
    private EditText editText;
    private TextView error;

    @SuppressLint("InflateParams")
    public UsernameDialog(Context context, IFragment iFragment, String account,String username) {
        super(context, R.style.TransDialog);
        Objects.requireNonNull(getWindow()).setBackgroundDrawableResource(R.color.transparent);

        View layout = getLayoutInflater().inflate(R.layout.dialog_username, null);
        setContentView(layout);
        find();
        editText.setText(username);
        editText.setSelection(username.length());

        button.setOnClickListener(v -> {
            String usernameChange = editText.getText().toString();
            if (usernameChange.isEmpty()) {
                error.setVisibility(View.VISIBLE);
            } else {
                UserOperate.modifyUser(context, account, "username", usernameChange);
                iFragment.backSet(iFragment.USERNAME_CHANGE, usernameChange);
                dismiss();
            }
        });
    }

    private void find() {
        button = findViewById(R.id.btnSureUND);
        editText = findViewById(R.id.etUsernameUND);
        error = findViewById(R.id.tvErrorUND);
    }
}