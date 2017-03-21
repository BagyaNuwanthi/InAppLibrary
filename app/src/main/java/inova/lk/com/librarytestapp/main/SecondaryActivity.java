package inova.lk.com.librarytestapp.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import inova.lk.com.inapplibrary.ads.AdRequest;
import inova.lk.com.inapplibrary.util.Utility;
import inova.lk.com.librarytestapp.R;
import inova.lk.com.librarytestapp.utils.Constants;

public class SecondaryActivity extends AppCompatActivity {

    WebView webView;
    String htmlText = " %s ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        webView = (WebView) findViewById(R.id.webView);
        webView.loadData(String.format(htmlText, getString(R.string.page_desc)), "text/html", "utf-8");

        int addType = getIntent().getExtras().getInt(Constants.ADD_TYPE);

        switch (addType){

            case R.id.fullScreenBanner:
                fullScreenBanner();
                break;

            case R.id.topBanner:
                topBanner();
                break;

            case R.id.bottomBanner:
                bottomBanner();
                break;

            case R.id.videoBanner:
                videoBanner();
                break;

            case R.id.allBanners:
                allBanners();
                break;
        }
    }

    private void allBanners() {

    }

    private void videoBanner() {

        AdRequest adRequest = new AdRequest(this);
        adRequest.enableVideoBanner(true);
    }

    private void bottomBanner() {

        AdRequest adRequest = new AdRequest(this);
        adRequest.enableBottomBanner(true);
    }

    private void topBanner() {

        AdRequest adRequest = new AdRequest(this);
        adRequest.enableTopBanner(true);
    }

    private void fullScreenBanner() {

        AdRequest adRequest = new AdRequest(this);
        adRequest.enableInterstitialBanner(true);
    }
}
