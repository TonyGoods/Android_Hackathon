package com.myapp.demomonth.View.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.myapp.demomonth.P.AnnouncementOperate;
import com.myapp.demomonth.R;

public class AnnouncementActivity extends Activity {

    private EditText etTitle;
    private EditText etContent;
    private Button btnMake;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
        find();

        btnMake.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String content = etContent.getText().toString();
            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, "内容未填写完整，不能发布！", Toast.LENGTH_SHORT).show();
            } else {
                if (AnnouncementOperate.make(this, title, content)) {
                    Toast.makeText(this, "发布成功！", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "公告已存在，不能再次发布！", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBack.setOnClickListener(v -> finish());
    }

    private void find() {
        etTitle = findViewById(R.id.announcement_title);
        etContent = findViewById(R.id.announcement_content);
        btnMake = findViewById(R.id.btn_make);
        btnBack = findViewById(R.id.btnBack);
    }
}