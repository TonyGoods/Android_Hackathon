package com.myapp.demomonth.View.Activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myapp.demomonth.P.PutFragment;
import com.myapp.demomonth.R;

public class MainActivity extends AppCompatActivity {

    private LinearLayout messageLayout;
    private LinearLayout friendsLayout;
    private LinearLayout findLayout;
    private LinearLayout iLayout;
    private TextView makeAnnouncement;
    private int chosenFlag = 0;
    private int message[] = new int[]{R.id.message_text, R.id.friends_text, R.id.find_text, R.id.i_text};
    private int images[] = new int[]{R.id.message_image, R.id.friends_image, R.id.find_image, R.id.i_image};
    private int imagesChosen[] = new int[]{R.mipmap.message_chosen, R.mipmap.friends_chosen, R.mipmap.find_chosen, R.mipmap.i_chosen};
    private int imagesNotChosen[] = new int[]{R.mipmap.message, R.mipmap.friends, R.mipmap.find, R.mipmap.i};
    private String account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        account = getIntent().getStringExtra("account");

        find();
        putFragment(-1, makeAnnouncement);
        messageLayout.setOnClickListener(v -> putFragment(0, makeAnnouncement));
        friendsLayout.setOnClickListener(v -> putFragment(1, makeAnnouncement));
        findLayout.setOnClickListener(v -> putFragment(2, makeAnnouncement));
        iLayout.setOnClickListener(v -> putFragment(3, makeAnnouncement));

        makeAnnouncement.setOnClickListener(v -> {
            Intent intent = new Intent(this, AnnouncementActivity.class);
            startActivity(intent);
        });
    }

    private void find() {
        messageLayout = findViewById(R.id.message_layout);
        friendsLayout = findViewById(R.id.friends_layout);
        findLayout = findViewById(R.id.find_layout);
        iLayout = findViewById(R.id.i_layout);
        makeAnnouncement = findViewById(R.id.tvMakeAnnouncement);
    }

    private void putFragment(int index, TextView makeAnnouncement) {
        if (index == chosenFlag) {
            return;
        } else if (index == -1) {
            index = 0;
        } else if (index == 2) {
            makeAnnouncement.setVisibility(View.VISIBLE);
        } else {
            makeAnnouncement.setVisibility(View.GONE);
        }

        ImageView imageView = findViewById(images[chosenFlag]);
        imageView.setBackground(getDrawable(imagesNotChosen[chosenFlag]));
        TextView textView = findViewById(message[chosenFlag]);
        textView.setTextColor(ContextCompat.getColor(this, R.color.unchosen));

        imageView = findViewById(images[index]);
        imageView.setBackground(getDrawable(imagesChosen[index]));
        textView = findViewById(message[index]);
        textView.setTextColor(ContextCompat.getColor(this, R.color.chosen));

        PutFragment.setFragment(this, account, index);

        chosenFlag = index;
    }
}