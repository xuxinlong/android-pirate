package com.pirate.activity.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.pirate.R;
import com.pirate.activity.WebViewJavascriptBridge.WebViewJavascriptBridge;

/**
 * Created by xuxinlong on 2018/6/12.
 */

public class WebViewActivity extends Activity {

    private WebView webView;
    private WebViewJavascriptBridge bridge;
    private String url = "http://47.104.157.93/blogs/index.html#/article/list";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView=(WebView) this.findViewById(R.id.webview);
        bridge=new WebViewJavascriptBridge(this, webView, new UserServerHandler());
        webView.loadUrl(url);
        registerHandle();

    }

    public static void startIntent(Context context) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    // 默认handler,防止调用没注册的bridge handler报错
    class UserServerHandler implements WebViewJavascriptBridge.WVJBHandler{
        @Override
        public void handle(String data, WebViewJavascriptBridge.WVJBResponseCallback jsCallback) {
            Log.d("test","Received message from javascript: "+ data);
            if (null !=jsCallback) {
                jsCallback.callback("Java said:Right back atcha");
            }
            bridge.send("I expect a response!",new WebViewJavascriptBridge.WVJBResponseCallback() {
                @Override
                public void callback(String responseData) {
                    Log.d("test","Got response! "+responseData);
                }
            });
            bridge.send("Hi");
        }
    }

    private void registerHandle(){
        bridge.registerHandler("handler1",new WebViewJavascriptBridge.WVJBHandler() {
            @Override
            public void handle(String data, WebViewJavascriptBridge.WVJBResponseCallback jsCallback) {
                Log.d("test","handler1 got:"+data);
                if(null!=jsCallback){
                    jsCallback.callback("handler1 answer");
                }
                bridge.callHandler("showAlert","42");
            }
        });
    }


}
