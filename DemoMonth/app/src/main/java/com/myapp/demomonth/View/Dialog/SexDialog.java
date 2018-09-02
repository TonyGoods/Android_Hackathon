package com.myapp.demomonth.View.Dialog;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.myapp.demomonth.P.UserOperate;
import com.myapp.demomonth.R;
import com.myapp.demomonth.View.Fragment.IFragment;

import java.util.Objects;

public class SexDialog extends AppCompatDialog {
    private RadioGroup radioGroup;
    private RadioButton radioMan;
    private RadioButton radioWoman;

    @SuppressLint("InflateParams")
    public SexDialog(Context context, IFragment iFragment, String account, String sex) {
        super(context, R.style.TransDialog);
        Objects.requireNonNull(getWindow()).setBackgroundDrawableResource(R.color.transparent);

        View layout = getLayoutInflater().inflate(R.layout.dialog_sex, null);
        setContentView(layout);

        find();
        if (sex.equals("男")) {
            radioMan.setChecked(true);
        } else if (sex.equals("女")) {
            radioWoman.setChecked(true);
        }

        radioGroup.setOnCheckedChangeListener((group, id) -> {
            String sexChange = ((RadioButton) Objects.requireNonNull((View) findViewById(id))).getText().toString();
            UserOperate.modifyUser(context, account, "sex", sexChange);
            iFragment.backSet(iFragment.SEX_CHANGE, sexChange);
            dismiss();
        });
    }

    private void find() {
        radioGroup = findViewById(R.id.radio_group);
        radioMan = findViewById(R.id.radio_man);
        radioWoman = findViewById(R.id.radio_woman);
    }
}