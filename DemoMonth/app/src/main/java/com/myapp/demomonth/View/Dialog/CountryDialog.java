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

public class CountryDialog extends AppCompatDialog {

    private Button button;
    private EditText editText;
    private TextView textView;

    @SuppressLint("InflateParams")
    public CountryDialog(Context context, IFragment iFragment, String account, String country) {
        super(context, R.style.TransDialog);
        Objects.requireNonNull(getWindow()).setBackgroundDrawableResource(R.color.transparent);

        View layout = getLayoutInflater().inflate(R.layout.dialog_country, null);
        setContentView(layout);
        find();

        editText.setText(country);
        editText.setSelection(country.length());

        button.setOnClickListener(v -> {
            String countryChange = editText.getText().toString();
            if (countryChange.isEmpty()) {
                textView.setVisibility(View.VISIBLE);
            } else {
                UserOperate.modifyUser(context, account, "country", countryChange);
                iFragment.backSet(iFragment.COUNTRY_CHANGE, countryChange);
                dismiss();
            }
        });
    }

    private void find() {
        button = findViewById(R.id.btnSureDC);
        editText = findViewById(R.id.etCountryDC);
        textView = findViewById(R.id.tvErrorDC);
    }
}