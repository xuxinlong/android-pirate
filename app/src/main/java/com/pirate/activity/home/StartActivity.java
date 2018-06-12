package com.pirate.activity.home;

import android.os.Bundle;

import com.pirate.activity.base.CustomBaseActivity;
import com.pirate.activity.base.WebViewActivity;
import com.pirate.activity.view.IStartView;
import com.pirate.R;

/**
 * Created by xuxinlong on 2018/2/7.
 */

public class StartActivity extends CustomBaseActivity implements IStartView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        jumpToHome();
    }
    @Override
    public void jumpToHome() {
        WebViewActivity.startIntent(this);
    }

    @Override
    public void jumpToAd() {

    }
}

