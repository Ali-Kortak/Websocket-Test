# Websocket Test



## What is the problem?

After repeating a certain process, that is explained later on, the webview of an application becomes noticeably slower and every action within the webviewer takes about five to ten seconds longer to load.

## What do you need to reconstruct the problem?

To reconstruct the problem you need a smartphone that has the Chrome Engine Version 78.0.3904 or newer. You need a webview that opens multiple websocket connections. We use "http://www.tobit.software/id/wallet". Since you have to be logged in to open this particular url you also need an account on that website. You need a method to reload the webview using the loadUrl() method from the webview.class. Javascript has to be enabled using webview.setJavascriptEnabled(true). And you also need to setup the webview using webView.setWebViewClient(). 

## How to reconstruct the problem?

The problem is reconstructed by using the loadUrl() method on a webview which loads a website that opens three or more Websockets. The problem occurs after different amounts of reloads. Sometimes it happens after the webview is reloaded once and sometimes it takes up to 400 reloads to occur. When the problem occurs the applications throws an Exception which is listed below. After the problem occurs the loadUrl() method takes more than one minute to reload the webview. Navigating through the webview to other pages of the website takes five to ten seconds longer.

## Reconstruct it Step-by-step with this application

1. Sign up on "http://www.tobit.software".
2. Start MainActivity of this Application.
3. Click the "RELOAD" button.
4. Sign into your account on the webview.
5. Start the ReloadWebViewTest class. The Application should now be calling the loadUrl() method.
6. Wait until the Exception listed below occurs. The webview should also stop reloading the webview since the loadUrl() method takes about one minute longer to reload the url. 
7. Now click on the navigation-drawer-icon and click on other menu options like "chayns.iD" opening different pages on the webview now take five to ten seconds longer.



## Exception:
```
E/GoogleApiClientConnecting: GoogleApiClient connecting is in step STEP_SERVICE_BINDINGS_AND_SIGN_IN but received callback for step STEP_GETTING_REMOTE_SERVICE java.lang.Exception 
at qw.b(PG:25) 
at qw.c(PG:36)
at Iw.c(PG:3) 
at Gx.c(PG:2) 
at ly.d(PG:15) 
at gy.a(PG:19) 
at iy.c(PG:6) 
at hy.handleMessage(PG:46) 
at android.os.Handler.dispatchMessage(Handler.java:112) 
at oG.dispatchMessage(PG:1) 
at android.os.Looper.loop(Looper.java:216) 
at android.os.HandlerThread.run(HandlerThread.java:65)
```

## Devices that have been tested and make the problem occur:

- Huawei P Smart (Android-Version 9) Chrome Engine Version 78.0.3904
- Huawei P 20 Pro Chrome Engine Version 78.0.3904
- Pixel (Android-Version 10) Chrome Engine Version 78.0.3904
- OnePlus 7t pro (Android-Version 10) Chrome Engine Version 79.0.3945
 
## Further information:

We also tried to make this problem occur by using the Chrome applications. We reloaded the page via the url textfield but the problem didn't occur. For that reason we think that the problem has to be connected to the webview. We also tried to replicate the problem on some older Chrome engines but that also didn't make the problem occur.




