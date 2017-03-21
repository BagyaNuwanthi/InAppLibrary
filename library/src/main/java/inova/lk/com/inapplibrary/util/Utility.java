package inova.lk.com.inapplibrary.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;

import inova.lk.com.inapplibrary.R;
import inova.lk.com.inapplibrary.ui.CustomWebView;

/**
 * Created by Milan on 2/23/17.
 */

public class Utility {

    public static void customDialogFullScreen(final Activity activity, String addUrl) {

        hideSystemUI(activity);

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_layout);
        dialog.setCancelable(false);

        Window window = dialog.getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
        wmlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wmlp.height = WindowManager.LayoutParams.MATCH_PARENT;

        CustomWebView customWebView = (CustomWebView) dialog.findViewById(R.id.customWebView);
        customWebView.getWebView().setWebChromeClient(new WebChromeClient(){

            public void onProgressChanged(WebView view, int progress) {
                if(progress == 100){

                    if(dialog != null && !dialog.isShowing())
                        dialog.show();
                }
            }
        });
        customWebView.setAddType("2");
        customWebView.setUrl(addUrl);

        ImageView closeView = (ImageView) dialog.findViewById(R.id.imageViewClose);
        closeView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        showSystemUI(activity);
                        dialog.dismiss();
                    }
                });

//        dialog.show();
    }

    public static void bannerTopBottom(final Activity activity, boolean isTopBanner, String addUrl) {

        final Dialog dialog = new Dialog(activity);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_layout);
        dialog.setCancelable(false);

        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    activity.onBackPressed();
                }
                return true;
            }
        });

        Window window = dialog.getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
        );

        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
        wmlp.width = WindowManager.LayoutParams.MATCH_PARENT;

        CustomWebView customWebView = (CustomWebView) dialog.findViewById(R.id.customWebView);
        customWebView.getWebView().setWebChromeClient(new WebChromeClient(){

            public void onProgressChanged(WebView view, int progress) {
                if(progress == 100){

                    if(dialog != null && !dialog.isShowing())
                        dialog.show();
                }
            }
        });
        customWebView.setUrl(addUrl);

        if(isTopBanner){

            customWebView.setAddType("0");
            wmlp.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
            wmlp.x = 0;   //x position

            int actionBarHeight = getActionBarSize(activity);
            if (actionBarHeight != -1) {
                wmlp.y = actionBarHeight;   //y position
            }
        }else {

            customWebView.setAddType("1");
            wmlp.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
            wmlp.x = 0;   //x position
        }


        ImageView closeView = (ImageView) dialog.findViewById(R.id.imageViewClose);
        closeView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });

//        dialog.show();
    }

    public static void hideSystemUI(Activity activity) {

        activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    public static void showSystemUI(Activity activity) {

        activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    public static int getActionBarSize(Activity activity){

        TypedValue tv = new TypedValue();

        if (activity.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {

            int actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, activity.getResources().getDisplayMetrics());
            return actionBarHeight;
        }

        return -1;
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
}
