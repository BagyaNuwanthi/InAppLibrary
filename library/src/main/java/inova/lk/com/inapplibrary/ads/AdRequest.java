package inova.lk.com.inapplibrary.ads;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import inova.lk.com.inapplibrary.ui.NativeBannerView;
import inova.lk.com.inapplibrary.util.Constants;
import inova.lk.com.inapplibrary.util.Utility;
import io.swagger.client.ApiException;
import io.swagger.client.api.AdvertisementsApi;
import io.swagger.client.model.AdvertisementRequest;
import io.swagger.client.model.AdvertisementResponse;

/**
 * Created by Milan on 3/17/17.
 */

public class AdRequest {

    private static final String TAG = AdRequest.class.getCanonicalName();
    private static final String TOKEN = "bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJnZXRBZCIsImFwcElkIjoiOTQifQ.gwq56dIqFP3BC9QT\n" +
            "GWXtpnGBYl-ecw61MLgVt-_griw";

    private final Activity activity;

    public AdRequest(Activity activity){

        this.activity = activity;
    }

    public void enableTopBanner(boolean isEnable){

        String[] params = {
                TOKEN, String.valueOf(Constants.ADD_TYPE_TOP)};
        new NetworkExecution().execute(params);
    }

    public void enableBottomBanner(boolean isEnable){

        String[] params = {
                TOKEN, String.valueOf(Constants.ADD_TYPE_BOTTOM)};
        new NetworkExecution().execute(params);
    }

    public void enableNativeBanner(NativeBannerView nativeBanner){

        String[] params = {
                TOKEN, String.valueOf(Constants.ADD_TYPE_NATIVE)};
        new NetworkExecution(nativeBanner).execute(params);
    }

    public void enableInterstitialBanner(boolean isEnable){

        String[] params = {
                TOKEN, String.valueOf(Constants.ADD_TYPE_INTERSTITIAL)};
        new NetworkExecution().execute(params);
    }

    public void enableVideoBanner(boolean isEnable){

        String[] params = {
                TOKEN, String.valueOf(Constants.ADD_TYPE_VIDEO)};
        new NetworkExecution().execute(params);
    }

    class NetworkExecution extends AsyncTask<String, Void, AdvertisementResponse> {

        private int addType;
        NativeBannerView nativeBanner;

        public NetworkExecution(){

        }

        public NetworkExecution(NativeBannerView nativeBanner){

            this.nativeBanner = nativeBanner;
        }

        @Override
        protected AdvertisementResponse doInBackground(String... params) {

            addType = Integer.parseInt(params[1]);
            AdvertisementsApi advertisementsApi = new AdvertisementsApi();
            try {
                AdvertisementRequest requestBody = new AdvertisementRequest();
                requestBody.setAddTypeId(params[1]);
                AdvertisementResponse advertisement = (AdvertisementResponse) advertisementsApi.fetchaddsPost(params[0], requestBody);

                return advertisement;
            } catch (TimeoutException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ApiException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(AdvertisementResponse advertisementResponse) {

            if (null != advertisementResponse){

                Log.d(TAG, advertisementResponse.getState().toString());
                Log.d(TAG, advertisementResponse.getMsg());


                if(Constants.RESPONSE_CODE_SUCCESS == advertisementResponse.getState()){

                    Log.d(TAG, advertisementResponse.getResponseObject().getContent());
                    switch (addType){

                        case Constants.ADD_TYPE_TOP:
                            Utility.bannerTopBottom(activity, true, advertisementResponse.getResponseObject().getContent());
                            break;

                        case Constants.ADD_TYPE_BOTTOM:
                            Utility.bannerTopBottom(activity, false, advertisementResponse.getResponseObject().getContent());
                            break;

                        case Constants.ADD_TYPE_NATIVE:

                            this.nativeBanner.loadData(advertisementResponse.getResponseObject().getContent());
                            break;

                        case Constants.ADD_TYPE_EXPANDABLE:
                            break;

                        case Constants.ADD_TYPE_VIDEO:
                            Utility.customDialogFullScreen(activity, advertisementResponse.getResponseObject().getContent());
                            break;

                        case Constants.ADD_TYPE_INTERSTITIAL:
                            Utility.customDialogFullScreen(activity, advertisementResponse.getResponseObject().getContent());
                            break;

                    }
                }else {

                    Log.d(TAG, "++++++++++ Error +++++++++++");
                }
            }else {

                Log.d(TAG, "++++++++++ null == AdvertisementResponse +++++++++++");
            }
        }
    }
}
