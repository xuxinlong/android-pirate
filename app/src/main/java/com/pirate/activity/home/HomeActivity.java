package com.pirate.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.pirate.R;
import com.pirate.activity.base.CustomBaseActivity;

public class HomeActivity extends CustomBaseActivity {
    WebView mWebView;

    private String url = "http://47.104.157.93/blogs/index.html#/article/list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 加载布局文件
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.wv_content);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebContentsDebuggingEnabled(true);
        mWebView.loadUrl(url);
    }


    public static void startIntent(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }
    @Override
    protected void onDestroy() {

        if (mWebView != null) {
            ViewGroup parent = (ViewGroup) mWebView.getParent();
            if (parent != null) {
                parent.removeView(mWebView);
            }
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }
}
