package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebsearchActivity extends AppCompatActivity {
    public static final String EXTRA_URL = "";

    private class MyCustomWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            if(Uri.parse(url).toString().startsWith("https://www.google.com/search?q=") && Uri.parse(url).toString().contains("tbm=isch")){
                return false;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyCustomWebViewClient());
        webView.loadUrl("https://www.google.com/search?q=cat&tbm=isch&source=lnms&sa=X");
    }
}
