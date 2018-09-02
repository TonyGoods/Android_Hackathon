package com.myapp.demomonth.P;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.myapp.demomonth.Model.Friend;
import com.myapp.demomonth.Model.Message;
import com.myapp.demomonth.R;
import com.myapp.demomonth.View.Activity.ChatActivity;

import java.util.List;

public class InitMessageFragment {
    private InitMessageFragment() {
    }

    public static void init(Context context, LinearLayout linearLayout, String account) {

        int WRAP_CONTENT = LayoutParams.WRAP_CONTENT;
        int MATCH_PARENT = LayoutParams.MATCH_PARENT;
        List<Friend> friends = UserOperate.getAll(context, account);
        for (Friend friend : friends) {
            Message message = MessageOperate.getLastMessage(context, account, friend.getAccount());

            LinearLayout mainLayout = ViewUtil.createLinearLayout(context, MATCH_PARENT, WRAP_CONTENT, new int[]{10, 10, 10, 5});
            mainLayout.setOrientation(LinearLayout.VERTICAL);
            mainLayout.setOnClickListener(v -> {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("account", account);
                intent.putExtra("friendAccount", friend.getAccount());
                intent.putExtra("friendName", friend.getUsername());
                context.startActivity(intent);
            });

            LinearLayout usernameLayout = ViewUtil.createLinearLayout(context, MATCH_PARENT, WRAP_CONTENT, null);

            TextView usernameTextView = ViewUtil.createTextView(context, WRAP_CONTENT, WRAP_CONTENT, null);
            usernameTextView.setText(friend.getUsername());
            usernameTextView.setTextColor(ContextCompat.getColor(context, R.color.black));
            usernameTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

            usernameLayout.addView(usernameTextView);
            mainLayout.addView(usernameLayout);

            LinearLayout mainMessageLayout = ViewUtil.createLinearLayout(context, MATCH_PARENT, WRAP_CONTENT, new int[]{0, 5, 0, 0});

            LinearLayout messageLayout = ViewUtil.createLinearLayout(context, WRAP_CONTENT, WRAP_CONTENT, new int[]{0, 5, 0, 0});
            messageLayout.setGravity(Gravity.START);

            String content;
            String time;
            if (message == null) {
                content = " ";
                time = " ";
            } else {
                content = message.getContent();
                if (content.length() > 14) {
                    content = content.substring(0, 14) + "...";
                }
                time = message.getTime();
                time = time.substring(5, 16);
            }

            TextView messageTextView = ViewUtil.createTextView(context, WRAP_CONTENT, WRAP_CONTENT, null);
            messageTextView.setText(content);

            messageLayout.addView(messageTextView);
            mainMessageLayout.addView(messageLayout);

            LinearLayout timeLayout = ViewUtil.createLinearLayout(context, MATCH_PARENT, WRAP_CONTENT, null);
            timeLayout.setGravity(Gravity.END);

            TextView timeTextView = ViewUtil.createTextView(context, WRAP_CONTENT, WRAP_CONTENT, null);
            timeTextView.setText(time);

            timeLayout.addView(timeTextView);
            mainMessageLayout.addView(timeLayout);

            mainLayout.addView(mainMessageLayout);

            linearLayout.addView(mainLayout);
        }
    }
}