package com.myapp.demomonth.P;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.myapp.demomonth.Model.Announcement;
import com.myapp.demomonth.R;
import com.myapp.demomonth.View.Activity.AnnouncementInfoActivity;

import java.util.List;

public class InitAnnouncementFragment {

    public InitAnnouncementFragment() {
    }

    public void init(Context context, LinearLayout listLayout) {
        int WRAP_CONTENT = LayoutParams.WRAP_CONTENT;
        int MATCH_PARENT = LayoutParams.MATCH_PARENT;

        listLayout.removeAllViews();

        List<Announcement> announcements = AnnouncementOperate.getAll(context);
        for (Announcement announcement : announcements) {
            SwipeMenuLayout swipeMenuLayout = ViewUtil.createSwipeMenuLayout(context, MATCH_PARENT, WRAP_CONTENT, null);
            swipeMenuLayout.setClickable(true);
            swipeMenuLayout.setIos(true);

            LinearLayout mainLayout = ViewUtil.createLinearLayout(context, MATCH_PARENT, WRAP_CONTENT, null);
            mainLayout.setOrientation(LinearLayout.VERTICAL);

            TextView announcementTextView = ViewUtil.createTextView(context, WRAP_CONTENT, WRAP_CONTENT, null);
            announcementTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            announcementTextView.setText(announcement.getTitle());
            announcementTextView.setPadding(30, 40, 30, 10);
            announcementTextView.setSingleLine(true);
            announcementTextView.setTextColor(ContextCompat.getColor(context, R.color.black));

            mainLayout.addView(announcementTextView);

            TextView timeTextView = ViewUtil.createTextView(context, WRAP_CONTENT, WRAP_CONTENT, null);
            timeTextView.setPadding(30, 0, 0, 10);
            timeTextView.setText(announcement.getTime());

            Button btnDelete = ViewUtil.createButton(context, WRAP_CONTENT, MATCH_PARENT, null);
            btnDelete.setBackgroundColor(ContextCompat.getColor(context, R.color.deleteRed));
            btnDelete.setText("删除");
            btnDelete.setTextColor(ContextCompat.getColor(context, R.color.white));
            btnDelete.setOnClickListener(v -> {
                AnnouncementOperate.delete(context, announcement.getTitle());
                Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                init(context, listLayout);
            });

            mainLayout.addView(timeTextView);
            mainLayout.setOnClickListener((v) -> {
                Intent intent = new Intent(context, AnnouncementInfoActivity.class);
                intent.putExtra("announcement_title", announcement.getTitle());
                context.startActivity(intent);

            });

            swipeMenuLayout.addView(mainLayout);
            swipeMenuLayout.addView(btnDelete);

            listLayout.addView(swipeMenuLayout);
        }
    }
}