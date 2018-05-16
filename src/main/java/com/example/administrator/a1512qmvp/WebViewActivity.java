package com.example.administrator.a1512qmvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    private WebView mWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initView();
        Intent intent = getIntent();
        String detailUrl = intent.getStringExtra("detailUrl");
        mWeb.loadUrl(detailUrl);

    }

    private void initView() {
        mWeb = (WebView) findViewById(R.id.web);
        WebSettings settings = mWeb.getSettings();
        settings.setJavaScriptEnabled(true);
    }
}
