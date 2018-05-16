package com.example.asus.jingdong.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.jingdong.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    /**
     * 跳过
     */
    private TextView btn;
    private RelativeLayout rel;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            timer.cancel();
            Intent intent = new Intent(MainActivity.this, showActivity.class);
            startActivity(intent);
            finish();

        }
    };
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                handler.sendMessage(message);
            }
        };
        timer.schedule(task,3000);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                Intent intent = new Intent(MainActivity.this, showActivity.class);

                startActivity(intent);
                finish();
            }
        });
    }

    private void initView() {
        btn = (TextView) findViewById(R.id.btn);
        rel = (RelativeLayout) findViewById(R.id.rel);
    }
}
