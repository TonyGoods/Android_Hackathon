package com.myapp.demomonth.P;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

public class ViewUtil {

    private ViewUtil() {
    }

    public static LinearLayout createLinearLayout(Context context, int width, int height, @Nullable int[] margin) {
        LinearLayout linearLayout = new LinearLayout(context);
        LayoutParams params = new LayoutParams(width, height);
        if (margin != null) {
            params.setMargins(margin[0], margin[1], margin[2], margin[3]);
        }
        linearLayout.setLayoutParams(params);
        return linearLayout;
    }

    public static TextView createTextView(Context context, int width, int height, @Nullable int[] margin) {
        TextView textView = new TextView(context);
        LayoutParams params = new LayoutParams(width, height);
        if (margin != null) {
            params.setMargins(margin[0], margin[1], margin[2], margin[3]);
        }
        textView.setLayoutParams(params);
        return textView;
    }

    public static ImageView createImageView(Context context, int width, int height, int[] margin) {
        ImageView image = new ImageView(context);
        LayoutParams params = new LayoutParams(width, height);
        if (margin != null) {
            params.setMargins(margin[0], margin[1], margin[2], margin[3]);
        }
        image.setLayoutParams(params);
        return image;
    }

    public static SwipeMenuLayout createSwipeMenuLayout(Context context, int width, int height, @Nullable int[] margin) {
        SwipeMenuLayout swipeMenuLayout = new SwipeMenuLayout(context);
        LayoutParams params = new LayoutParams(width, height);
        if (margin != null) {
            params.setMargins(margin[0], margin[1], margin[2], margin[3]);
        }
        swipeMenuLayout.setLayoutParams(params);
        return swipeMenuLayout;
    }

    public static Button createButton(Context context, int width, int height, @Nullable int[] margin) {
        Button button = new Button(context);
        LayoutParams params = new LayoutParams(width, height);
        if (margin != null) {
            params.setMargins(margin[0], margin[1], margin[2], margin[3]);
        }
        button.setLayoutParams(params);
        return button;
    }
}
