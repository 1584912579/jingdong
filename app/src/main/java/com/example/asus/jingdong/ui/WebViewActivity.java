package com.example.asus.jingdong.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.asus.jingdong.R;

public class WebViewActivity extends AppCompatActivity {
    private WebView mWv;
    private String detailUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        //接收地址
        Intent intent = getIntent();
        detailUrl = intent.getStringExtra("detailUrl");
        initView();
        mWv.loadUrl(detailUrl);
    }
    private void initView() {
        mWv = (WebView) findViewById(R.id.wv);
        WebSettings settings = mWv.getSettings();
        //支持js
        settings.setJavaScriptEnabled(true);
    }
}
