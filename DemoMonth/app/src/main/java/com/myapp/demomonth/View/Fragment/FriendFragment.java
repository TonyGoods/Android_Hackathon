package com.myapp.demomonth.View.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.myapp.demomonth.P.GetContacts;
import com.myapp.demomonth.P.ViewUtil;
import com.myapp.demomonth.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class FriendFragment extends Fragment {
    private List<String> list = new ArrayList<>();
    private EditText etName;
    private String[] phoneName;
    private TextView noMatch;
    private LinearLayout fun;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_friend, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        noMatch = getActivity().findViewById(R.id.noMatch);

        etName = getActivity().findViewById(R.id.etName);
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String name = etName.getText().toString();
                list = Arrays.stream(phoneName)
                        .filter(str -> str.contains(name))
                        .map(str -> str.replace(name, "<font color=\"red\">" + name + "</font>"))
                        .collect(toList());
                if (list.isEmpty()) {
                    noMatch.setVisibility(View.VISIBLE);
                } else {
                    noMatch.setVisibility(View.GONE);
                }
                set();
            }
        });
        init();
    }

    private void init() {
        Activity activity = getActivity();
        fun = activity.findViewById(R.id.list_fragment);

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CONTACTS},
                    0);
        }
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.GET_ACCOUNTS) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.GET_ACCOUNTS}, 0);
        }

        if (list.isEmpty()) {
            phoneName = GetContacts.get(getContext());
            Collections.addAll(list, phoneName);
        }
        set();
    }

    private void set() {
        int MATCH_PARENT = LayoutParams.MATCH_PARENT;
        int WRAP_CONTENT = LayoutParams.WRAP_CONTENT;
        fun.removeAllViews();
        for (String name : list) {
            TextView textView = ViewUtil.createTextView(getContext(), MATCH_PARENT, WRAP_CONTENT, new int[]{0, 50, 0, 0});
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setPadding(15, 0, 15, 0);
            textView.setText(Html.fromHtml(name, Html.FROM_HTML_MODE_COMPACT));
            textView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.border_friend));
            fun.addView(textView);
        }
    }
}