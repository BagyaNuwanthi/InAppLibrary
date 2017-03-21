package inova.lk.com.inapplibrary.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import inova.lk.com.inapplibrary.R;

/**
 * Created by Milan on 2/16/17.
 */

public class NativeBannerView extends RelativeLayout {

    private WebView myWebView;
    private TextView textViewHeader;
    String contentUrl;

    public NativeBannerView(Context context) {

        super(context);
        initializeViews(context);
    }

    public NativeBannerView(Context context, AttributeSet attrs) {

        super(context, attrs);
        initializeViews(context);
    }

    public NativeBannerView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
        initializeViews(context);
    }

    private void initializeViews(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.native_banner, this);
    }

    @Override
    protected void onFinishInflate() {

        super.onFinishInflate();
        textViewHeader = (TextView) this.findViewById(R.id.textViewHeader);
        myWebView = (WebView) this.findViewById(R.id.nativeBannerContent);

//        myWebView.setWebViewClient(new WebViewClient());
//        myWebView.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        final WebSettings settings = myWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setPluginState(WebSettings.PluginState.ON);

        myWebView.setWebViewClient(new WebViewClient() {
            // autoplay when finished loading via javascript injection
            public void onPageFinished(WebView view, String url) {
                myWebView.loadUrl("javascript:(function() { document.getElementsByTagName('video')[0].play(); })()");
            }
        });
        myWebView.setWebChromeClient(new WebChromeClient());
        myWebView.getSettings().setMediaPlaybackRequiresUserGesture(false);

        myWebView.loadUrl(contentUrl);
        textViewHeader.setVisibility(GONE);
        this.myWebView.setVisibility(VISIBLE);
    }

    public void setUrl(String addUrl) {

        this.contentUrl = addUrl;
    }

    public String getUrl() {

        return contentUrl;
    }

    public void loadData(String content){

        this.myWebView.loadData(content,"text/html", "UTF-8");
    }
}
