package com.myapp.demomonth.P;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.myapp.demomonth.R;
import com.myapp.demomonth.View.Fragment.FindFragment;
import com.myapp.demomonth.View.Fragment.FriendFragment;
import com.myapp.demomonth.View.Fragment.IFragment;
import com.myapp.demomonth.View.Fragment.MessageFragment;

public class PutFragment {
    @SuppressLint("StaticFieldLeak")
    private static MessageFragment messageFragment = new MessageFragment();
    @SuppressLint("StaticFieldLeak")
    private static FriendFragment friendFragment = new FriendFragment();
    @SuppressLint("StaticFieldLeak")
    private static FindFragment findFragment = new FindFragment();
    @SuppressLint("StaticFieldLeak")
    private static IFragment iFragment = new IFragment();
    private static Fragment[] fragments = new Fragment[]{messageFragment, friendFragment, findFragment, iFragment};

    private PutFragment() {
    }

    public static void setFragment(AppCompatActivity activity, String account, int index) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("account", account);
        fragments[index].setArguments(bundle);
        transaction.replace(R.id.fragment, fragments[index]);
        transaction.commit();
    }
}