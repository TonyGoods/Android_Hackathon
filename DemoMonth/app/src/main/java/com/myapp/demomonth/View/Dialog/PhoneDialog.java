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

public class PhoneDialog extends AppCompatDialog {

    private Button button;
    private EditText editText;
    private TextView textView;

    @SuppressLint("InflateParams")
    public PhoneDialog(Context context, IFragment iFragment, String account, String phone) {
        super(context, R.style.TransDialog);
        Objects.requireNonNull(getWindow()).setBackgroundDrawableResource(R.color.transparent);

        View layout = getLayoutInflater().inflate(R.layout.dialog_phone, null);
        setContentView(layout);
        find();

        editText.setText(phone);
        editText.setSelection(phone.length());

        button.setOnClickListener(v -> {
            String phoneChange = editText.getText().toString();
            if (phoneChange.isEmpty()) {
                textView.setVisibility(View.VISIBLE);
            } else {
                UserOperate.modifyUser(context, account, "phone", phoneChange);
                iFragment.backSet(iFragment.PHONE_CHANGE, phoneChange);
                dismiss();
            }
        });
    }

    private void find() {
        button = findViewById(R.id.btnSureDP);
        editText = findViewById(R.id.etPhoneDP);
        textView = findViewById(R.id.tvErrorDP);
    }
}