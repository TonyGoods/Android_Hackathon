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


public class AgeDialog extends AppCompatDialog {

    private Button button;
    private EditText editText;
    private TextView textView;

    @SuppressLint("InflateParams")
    public AgeDialog(Context context, IFragment iFragment, String account,String age) {
        super(context, R.style.TransDialog);
        Objects.requireNonNull(getWindow()).setBackgroundDrawableResource(R.color.transparent);

        View layout = getLayoutInflater().inflate(R.layout.dialog_age, null);
        setContentView(layout);
        find();

        editText.setText(age);
        editText.setSelection(age.length());

        button.setOnClickListener(v -> {
            String ageChange = editText.getText().toString();
            if (ageChange.isEmpty()) {
                textView.setVisibility(View.VISIBLE);
            } else {
                UserOperate.modifyUser(context, account, "age", ageChange);
                iFragment.backSet(iFragment.AGE_CHANGE, ageChange);
                dismiss();
            }

        });
    }

    private void find() {
        editText = findViewById(R.id.etAgeDA);
        button = findViewById(R.id.btnSureDA);
        textView = findViewById(R.id.tvErrorDA);
    }
}