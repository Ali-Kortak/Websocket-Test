package com.akortak.android.testproject;


import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import java.time.Duration;
import java.time.LocalTime;


public class MainActivity extends AppCompatActivity {
    LocalTime tsLoad;
    LocalTime tsPageStarted;
    static int counter= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WebView myWebView = (WebView) findViewById(R.id.webView);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebContentsDebuggingEnabled(true);
        Button reloadButton = (Button)findViewById(R.id.button);
        reloadButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tsLoad= java.time.LocalTime.now();
                myWebView.loadUrl("http://www.tobit.software/id/wallet");
            }});

        myWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                tsPageStarted = java.time.LocalTime.now();
                System.out.println(Duration.between(tsLoad, tsPageStarted) + " Amount of Reloads " + counter++);
                super.onPageStarted(view, url, favicon);
            }
        });
    }
}

