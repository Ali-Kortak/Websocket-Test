package com.akortak.android.testproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import java.time.Duration;
import java.time.LocalTime;

import javax.net.ssl.HandshakeCompletedListener;


public class MainActivity extends AppCompatActivity {
    LocalTime pageStarted;
    LocalTime pageFinished;
    Button reloadButton;
    Button loginButton;
    int reloadCounter;
    long timer =0;

    public void reload(final WebView wv){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                if(timer < 3500) {
                    Toast.makeText(MainActivity.this, "Webview reloading!", Toast.LENGTH_LONG).show();
                    wv.loadUrl("http://www.tobit.software/id/wallet");
                } else{
                    Toast.makeText(MainActivity.this, "The Exception occured! Try navigating through the Webview now.", Toast.LENGTH_LONG).show();
                }
                reload(wv);
            }
        },1000);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WebView myWebView = (WebView) findViewById(R.id.webView);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebContentsDebuggingEnabled(true);
        reloadButton = (Button) findViewById(R.id.button);
        reloadButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                reload(myWebView);
            }
        });

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                myWebView.loadUrl("http://www.tobit.software/id/wallet");
            }
        });

        myWebView.setWebViewClient(new WebViewClient() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pageStarted = LocalTime.now();
                Log.e("Amout of reloads", " " + reloadCounter++);
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onPageFinished(WebView view, String url) {
                pageFinished = LocalTime.now();
                System.out.println(Duration.between(pageStarted, pageFinished));
                timer = Duration.between(pageStarted, pageFinished).toMillis();
                super.onPageFinished(view, url);
            }
        });
    }
}