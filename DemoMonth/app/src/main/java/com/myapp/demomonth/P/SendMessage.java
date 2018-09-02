package com.myapp.demomonth.P;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myapp.demomonth.R;

public class SendMessage {
    private SendMessage() {
    }

    public static void send(Context context, LinearLayout messageLayout, String message) {
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 10, 0, 10);
        linearLayout.setLayoutParams(layoutParams);

        TextView textView = new TextView(context);
        textView.setPadding(30, 30, 30, 30);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 10, 0, 10);
        textView.setLayoutParams(params);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        textView.setText(message);
        if (message.length() > 13) {
            textView.setEms(13);
        }
        textView.setTextColor(context.getColor(R.color.black));

        ImageView image = new ImageView(context);
        image.setLayoutParams(params);

        textView.setBackground(context.getDrawable(R.drawable.round_corner_send));
        image.setBackground(context.getDrawable(R.mipmap.message_send));
        linearLayout.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
        linearLayout.addView(textView);
        linearLayout.addView(image);

        messageLayout.addView(linearLayout);
    }
}