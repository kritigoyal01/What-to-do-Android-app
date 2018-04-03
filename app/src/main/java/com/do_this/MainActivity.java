package com.do_this;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.do_this.model.Count;
import com.do_this.model.Message;
import com.do_this.util.Helper;
import com.do_this.util.SharedPreferenceUtil;
import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView message;
    private ArrayList<Message> messageList;
    private Count displayedCount;
    private SharedPreferenceUtil sharedPreferenceUtil;
    private FlowLayout progressContainer1, progressContainer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        pickRandomMessage();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                message.setVisibility(View.VISIBLE);
                progressContainer1.setVisibility(View.GONE);
                progressContainer2.setVisibility(View.GONE);
            }
        }, 1000);
    }

    private void init() {
        sharedPreferenceUtil = new SharedPreferenceUtil(this);
        messageList = Helper.getMessages(sharedPreferenceUtil);
        displayedCount = Helper.getDisplayedCount(sharedPreferenceUtil);
        displayedCount.resetIfADayOld();

        message = findViewById(R.id.message);
        progressContainer1 = findViewById(R.id.progressContainer1);
        progressContainer2 = findViewById(R.id.progressContainer2);
    }

    public void pickRandomMessage() {
        Message randomMessage = messageList.get(new Random().nextInt(messageList.size()));
        if (randomMessage.isADayOld()) {
            message.setText(randomMessage.getMessage());
            displayedCount.incrementCount();
            randomMessage.setLastDisplayTime(System.currentTimeMillis());
        } else if (messageList.size() != displayedCount.getCount()) {
            pickRandomMessage();
        } else {
            message.setText("What! Seriously? how can someone hear so much bad stuff about him/herself in one day...\nPlease comeback tomorrow for more insult :-)");
        }
        Helper.setDisplayedCount(sharedPreferenceUtil, displayedCount);
        Helper.setMessages(sharedPreferenceUtil, messageList);
    }
}
