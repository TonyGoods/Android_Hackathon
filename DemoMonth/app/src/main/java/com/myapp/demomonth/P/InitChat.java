package com.myapp.demomonth.P;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.myapp.demomonth.Model.Message;
import com.myapp.demomonth.R;

import java.util.List;

public class InitChat {
    private InitChat() {
    }

    public static void init(Context context, LinearLayout messageLayout, String account, String friendAccount) {
        int WRAP_CONTENT = LayoutParams.WRAP_CONTENT;
        int MATCH_PARENT = LayoutParams.MATCH_PARENT;

        List<Message> messages = MessageOperate.getMessages(context, account, friendAccount);
        int[] margin = new int[]{0, 10, 0, 10};
        for (Message message : messages) {

            LinearLayout linearLayout = ViewUtil.createLinearLayout(context, MATCH_PARENT, WRAP_CONTENT, margin);

            TextView textView = ViewUtil.createTextView(context, WRAP_CONTENT, WRAP_CONTENT, margin);
            textView.setPadding(30, 30, 30, 30);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            textView.setText(message.getContent());
            textView.setTextColor(context.getColor(R.color.black));
            if (message.getContent().length() > 13) {
                textView.setEms(13);
            }

            ImageView image = ViewUtil.createImageView(context, WRAP_CONTENT, WRAP_CONTENT, new int[]{0, 35, 0, 0});

            if (message.getReceiver().equals(account)) {
                textView.setBackground(context.getDrawable(R.drawable.round_corner_receive));
                image.setBackground(context.getDrawable(R.mipmap.message_receive));
                linearLayout.addView(image);
                linearLayout.addView(textView);
            } else {
                textView.setBackground(context.getDrawable(R.drawable.round_corner_send));
                image.setBackground(context.getDrawable(R.mipmap.message_send));
                linearLayout.setGravity(Gravity.END);
                linearLayout.addView(textView);
                linearLayout.addView(image);
            }
            messageLayout.addView(linearLayout);
        }
    }
}