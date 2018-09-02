package com.myapp.demomonth.View.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.myapp.demomonth.P.InitAnnouncementFragment;
import com.myapp.demomonth.R;

public class FindFragment extends Fragment {
    private LinearLayout listLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_find, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listLayout = getActivity().findViewById(R.id.find_list);
    }

    @Override
    public void onResume() {
        super.onResume();
        new InitAnnouncementFragment().init(getContext(), listLayout);
    }
}