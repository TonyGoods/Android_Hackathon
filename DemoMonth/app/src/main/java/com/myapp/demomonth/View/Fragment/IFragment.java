package com.myapp.demomonth.View.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myapp.demomonth.Model.User;
import com.myapp.demomonth.P.HasLoginOperate;
import com.myapp.demomonth.P.UserOperate;
import com.myapp.demomonth.R;
import com.myapp.demomonth.View.Activity.LoginActivity;
import com.myapp.demomonth.View.Dialog.AgeDialog;
import com.myapp.demomonth.View.Dialog.CountryDialog;
import com.myapp.demomonth.View.Dialog.PhoneDialog;
import com.myapp.demomonth.View.Dialog.SexDialog;
import com.myapp.demomonth.View.Dialog.UsernameDialog;

public class IFragment extends Fragment {

    private Button btnQuit;
    private String account;
    private TextView tvAccount;
    private TextView tvUsername;
    private TextView tvSex;
    private TextView tvAge;
    private TextView tvCountry;
    private TextView tvPhone;

    private LinearLayout usernameLayout;
    private LinearLayout sexLayout;
    private LinearLayout ageLayout;
    private LinearLayout phoneLayout;
    private LinearLayout countryLayout;

    public final int USERNAME_CHANGE = 1;
    public final int SEX_CHANGE = 2;
    public final int AGE_CHANGE = 3;
    public final int COUNTRY_CHANGE = 4;
    public final int PHONE_CHANGE = 5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_i, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        account = getArguments().getString("account");
        find();
        init();

        btnQuit.setOnClickListener(v -> {
            HasLoginOperate.delete(getContext());
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        });

        usernameLayout.setOnClickListener(v -> new UsernameDialog(getContext(), this, account, tvUsername.getText().toString()).show());
        sexLayout.setOnClickListener(v -> new SexDialog(getContext(), this, account, tvSex.getText().toString()).show());
        ageLayout.setOnClickListener(v -> new AgeDialog(getContext(), this, account, tvAge.getText().toString()).show());
        phoneLayout.setOnClickListener(v -> new PhoneDialog(getContext(), this, account, tvPhone.getText().toString()).show());
        countryLayout.setOnClickListener(v -> new CountryDialog(getContext(), this, account, tvCountry.getText().toString()).show());
    }

    private void init() {
        User user = UserOperate.queryUser(getContext(), account);
        tvAccount.setText(user.getAccount());
        tvUsername.setText(user.getUsername());
        tvSex.setText(user.getSex());
        tvAge.setText(user.getAge());
        tvCountry.setText(user.getCountry());
        tvPhone.setText(user.getPhone());
    }

    private void find() {
        Activity activity = getActivity();
        btnQuit = activity.findViewById(R.id.quitBtn);

        tvAccount = activity.findViewById(R.id.i_account);
        tvUsername = activity.findViewById(R.id.i_username);
        tvAge = activity.findViewById(R.id.i_age);
        tvSex = activity.findViewById(R.id.i_sex);
        tvCountry = activity.findViewById(R.id.i_country);
        tvPhone = activity.findViewById(R.id.i_phone);

        usernameLayout = activity.findViewById(R.id.i_layout_username);
        sexLayout = activity.findViewById(R.id.i_layout_sex);
        ageLayout = activity.findViewById(R.id.i_layout_age);
        phoneLayout = activity.findViewById(R.id.i_layout_phone);
        countryLayout = activity.findViewById(R.id.i_layout_country);
    }

    public void backSet(int flag, String message) {
        switch (flag) {
            case USERNAME_CHANGE:
                tvUsername.setText(message);
                break;
            case SEX_CHANGE:
                tvSex.setText(message);
                break;
            case AGE_CHANGE:
                tvAge.setText(message);
                break;
            case COUNTRY_CHANGE:
                tvCountry.setText(message);
                break;
            case PHONE_CHANGE:
                tvPhone.setText(message);
                break;
        }
    }
}