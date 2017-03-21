package inova.lk.com.librarytestapp.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import inova.lk.com.inapplibrary.ads.AdRequest;
import inova.lk.com.inapplibrary.ui.NativeBannerView;
import inova.lk.com.inapplibrary.util.Utility;
import inova.lk.com.librarytestapp.R;

public class NativeBannerActivity extends AppCompatActivity {

    NativeBannerView nativeBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_banner);

        nativeBanner = (NativeBannerView) findViewById(R.id.nativeBanner);
        AdRequest adRequest = new AdRequest(this);
        adRequest.enableNativeBanner(nativeBanner);

        Utility.bannerTopBottom(this, true, "<!DOCTYPE html><html lang='en'><head><meta charset='utf-8'><meta http-equiv='X-UA-Compatible' content='IE=edge'><meta name='viewport' content='width=device-width, initial-scale=1'><link href='http://dev.inovaitsys.com:18085/InAppInWeb/resources/custom/css/video-responsive.css' rel='stylesheet' type='text/css' /></head><body><div class='fullscreen-bg'><a target='_blank' href='https://www.google.lk/'><video class='fullscreen-bg__video' autoplay loop><source src='http://dev.inovaitsys.com:18085/InAppInWeb/contents/ORG0001892017-03-17-11-27-08.mp4' /></video></a></div></body></html>");
//        nativeBanner.setUrl("http://signage.dialog.lk/default-videos/contents/Survivor%20Worlds%20Apart%2020-sec%20TV%20ad2016-09-06-17-29-46_ORG000003_2017-01-27-09-49-13.mp4");
//        nativeBanner.setUrl("https://www.bigcartel.com/");
//        nativeBanner.loadData("<!DOCTYPE html><html lang='en'><head><meta charset='utf-8'><meta http-equiv='X-UA-Compatible' content='IE=edge'><meta name='viewport' content='width=device-width, initial-scale=1'><link href='http://dev.inovaitsys.com:18085/InAppInWeb/resources/custom/css/video-responsive.css' rel='stylesheet' type='text/css' /></head><body><div class='fullscreen-bg'><a target='_blank' href='https://www.google.lk/'><video class='fullscreen-bg__video' autoplay loop><source src='http://dev.inovaitsys.com:18085/InAppInWeb/contents/ORG0001892017-03-17-11-27-08.mp4' /></video></a></div></body></html>");
    }
}
