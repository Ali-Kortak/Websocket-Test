package com.akortak.android.testproject;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import java.time.Duration;
import java.time.LocalTime;


public class MainActivity extends AppCompatActivity {
    LocalTime pageStarted;
    LocalTime pageFinished;
    static int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WebView myWebView = (WebView) findViewById(R.id.webView);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebContentsDebuggingEnabled(true);
        Button reloadButton = (Button) findViewById(R.id.button);
        reloadButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    myWebView.loadUrl("http://www.tobit.software/id/wallet");
            }
        });
            myWebView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    System.out.println(" Amount of Reloads " + counter++);
                    super.onPageStarted(view, url, favicon);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        pageStarted = LocalTime.now();
                    }
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        pageFinished = LocalTime.now();
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        System.out.println(Duration.between(pageStarted,pageFinished));
                        if (Duration.between(pageStarted,pageFinished).toMillis()>2000){
                            Log.e("Exception occured", "Try navigation through the webview");
                        }
                    }

                }
            });
    }
}


