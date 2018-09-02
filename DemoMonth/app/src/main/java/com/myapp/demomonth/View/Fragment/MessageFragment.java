package com.myapp.demomonth.View.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.myapp.demomonth.P.InitMessageFragment;
import com.myapp.demomonth.R;

public class MessageFragment extends Fragment {

    private LinearLayout linearLayout;
    private String account;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        account = getArguments().getString("account");
        find();
    }

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

    private void find() {
        Activity activity = getActivity();
        linearLayout = activity.findViewById(R.id.message_char_layout);
    }

    private void init() {
        linearLayout.removeAllViews();
        InitMessageFragment.init(getContext(), linearLayout, account);
    }
}