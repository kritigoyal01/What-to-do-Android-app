package com.example.kritig.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView t1;
    Button b1;
    ArrayList<Message> list = new ArrayList<Message>();
    Count c1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRandom();
            }
        });
    }

    private void init() {
        list.add(new Message("Cooking"));
        list.add(new Message("Walking"));
        list.add(new Message("Kiss Me"));
        list.add(new Message("Fuck Off"));
        list.add(new Message("Love You"));
        list.add(new Message("Check Emails"));
        list.add(new Message("Bathing"));
        list.add(new Message("Shopping"));
        list.add(new Message("Sleeping Time"));
        list.add(new Message("Doing Nothing"));
        list.add(new Message("All play and No work"));
        list.add(new Message("Wasting time"));
        list.add(new Message("Watching Movie"));
        list.add(new Message("Go for outing to chill"));
        list.add(new Message("Fun time"));
        list.add(new Message("Anger"));
        //c1.setLast_incremented_date(System.currentTimeMillis());
        c1.setLast_incremented_date(0);
        c1.setCount(0);
        t1 = findViewById(R.id.text1);
        b1 = findViewById(R.id.button1);
    }

    public void myRandom() {
        Random random = new Random();
        int index = random.nextInt(list.size());
        if (list.get(index).getLast_display_time() == 0) {
            t1.setText(list.get(index).getMessage());
            c1.updateCount();
            list.get(index).setLast_display_time(System.currentTimeMillis());
        } else {
            long diff2 = (System.currentTimeMillis() - list.get(index).getLast_display_time()) - (86400 * 1000);
            if (diff2 > 0) {
                t1.setText(list.get(index).getMessage());
                c1.updateCount();
                list.get(index).setLast_display_time(System.currentTimeMillis());
            } else if (list.size() != c1.getCount()) {
                myRandom();
            } else {
                t1.setText("BOHOT HO GYA AAJ KAAM");
            }
        }
    }
}
