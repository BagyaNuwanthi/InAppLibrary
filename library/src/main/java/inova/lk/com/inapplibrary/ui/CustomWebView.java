package inova.lk.com.inapplibrary.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import inova.lk.com.inapplibrary.R;
import inova.lk.com.inapplibrary.util.Utility;

/**
 * Created by Milan on 2/16/17.
 */

public class CustomWebView extends RelativeLayout {

    private WebView myWebView;
    String addType;
    String data;

    public CustomWebView(Context context) {

        super(context);
        initializeViews(context);
    }

    public CustomWebView(Context context, AttributeSet attrs) {

        super(context, attrs);
        TypedArray theAttrs = context.obtainStyledAttributes(attrs, R.styleable.CustomWebView);
        addType = theAttrs.getString(R.styleable.CustomWebView_addType);
        theAttrs.recycle();

        initializeViews(context, addType);
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
        TypedArray theAttrs = context.obtainStyledAttributes(attrs, R.styleable.CustomWebView);
        addType = theAttrs.getString(R.styleable.CustomWebView_addType);
        theAttrs.recycle();
        initializeViews(context, addType);
    }

    private void initializeViews(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.inapp_webview, this);
    }

    private void initializeViews(Context context, String temp) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.inapp_webview, this);
    }

    @Override
    protected void onFinishInflate() {

        super.onFinishInflate();
        myWebView = (WebView) this.findViewById(R.id.inAPpWebView);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.getSettings().setJavaScriptEnabled(true);
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
        myWebView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        myWebView.loadUrl(data);
//        this.setGravity(Gravity.CENTER);

        if (Integer.parseInt(addType) == 0 || Integer.parseInt(addType) == 1) {

            this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            myWebView.getLayoutParams().width = LayoutParams.MATCH_PARENT;
        } else if (Integer.parseInt(addType) == 2 || Integer.parseInt(addType) == 3) {

            this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            myWebView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }else if (Integer.parseInt(addType) == 4) {

            this.setLayoutParams(this.getLayoutParams());
            myWebView.setLayoutParams(new LayoutParams(Utility.dpToPx(200), Utility.dpToPx(200)));
        }
    }

    public void setAddType(String addType) {

        this.addType = addType;
    }

    public String getAddType() {

        return addType;
    }

    public void setUrl(String data) {

        this.data = data;
        this.myWebView.loadData(data, "text/html", "UTF-8");
    }

    public String getUrl() {

        return addType;
    }

    public WebView getWebView() {

        return myWebView;
    }
}
