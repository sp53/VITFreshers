package com.vitfreshers.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class FAQAndForm extends AppCompatActivity {

    WebView disp;
    ProgressBar pbr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_and_form);

        Toolbar toolbar = findViewById(R.id.toolbar_faq_form_activity);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("VIT Freshers");

        pbr = findViewById(R.id.progressBar2);

        disp = findViewById(R.id.webView);
        disp.getSettings().setJavaScriptEnabled(true);
        disp.loadUrl("http://vitappapi.herokuapp.com/faqffcs.php");

        disp.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    pbr.setVisibility(View.GONE);

                } else {
                    pbr.setVisibility(View.VISIBLE);
                }
            }
        });
        //disp.loadUrl("https://forms.gle/bYTuXW9TdMnngypz9");
    }

    public void open_hostel_faqs(View view)
    {
        String pdf = "https://plasma2k19.000webhostapp.com/vitappapi/hostelfaq.pdf";
        disp.loadUrl("https://docs.google.com/viewer?url=" + pdf);
        //disp.loadUrl("http://vitappapi.herokuapp.com/faqhostel.php");

    }

    public void open_ffcs_faqs(View view)
    {
        disp.loadUrl("http://vitappapi.herokuapp.com/faqffcs.php");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}