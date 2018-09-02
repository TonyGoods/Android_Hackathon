package com.myapp.demomonth.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.myapp.demomonth.Model.Announcement;
import com.myapp.demomonth.P.AnnouncementOperate;
import com.myapp.demomonth.R;

public class AnnouncementInfoActivity extends AppCompatActivity {

    private TextView title;
    private TextView content;
    private TextView time;
    private ImageButton btnBack;
    private String titleStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_info);
        titleStr = getIntent().getStringExtra("announcement_title");
        find();
        init();

        btnBack.setOnClickListener(v -> finish());
    }

    private void init() {
        Announcement announcement = AnnouncementOperate.get(this, titleStr);
        title.setText(announcement.getTitle());
        time.setText(announcement.getTime());
        content.setText(announcement.getContent());
    }

    private void find() {
        btnBack = findViewById(R.id.btnBackInfo);
        title = findViewById(R.id.announcement_title_info);
        content = findViewById(R.id.announcement_content_info);
        time = findViewById(R.id.announcement_time_info);
    }
}
