package com.example.androidadmobdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;

import androidx.appcompat.app.AppCompatActivity;

public class NativeAdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_ad);

        AdLoader adLoader = new AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110")
                .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        //the native ad will be available inside this method  (unifiedNativeAd)


                        UnifiedNativeAdView unifiedNativeAdView = (UnifiedNativeAdView) getLayoutInflater().inflate(R.layout.native_ad_layout, null);

                        mapUnifiedNativeAdToLayout(unifiedNativeAd, unifiedNativeAdView);

                        FrameLayout nativeAdLayout = findViewById(R.id.id_native_ad);
                        nativeAdLayout.removeAllViews();
                        nativeAdLayout.addView(unifiedNativeAdView);

                    }
                })
                .withAdListener(new AdListener() {
                    // Code to be executed when an ad request fails.
                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        Toast.makeText(getApplicationContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
                    }

                    //method is invoked when the rewarded ad is closed
                    // due to the user tapping on the close icon or using the back button.
                    @Override
                    public void onAdClosed() {
                        Toast.makeText(getApplicationContext(), "Ad is closed!", Toast.LENGTH_SHORT).show();
                    }

                    //called after onAdOpened(), when a user click opens another app,
                    //backgrounding the current app
                    @Override
                    public void onAdLeftApplication() {
                        Toast.makeText(getApplicationContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
                    }

                    //Code to be executed when an ad opens an overlay that
                    // covers the screen.
                    @Override
                    public void onAdOpened() {
                        Toast.makeText(getApplicationContext(), "Ad is opened!", Toast.LENGTH_SHORT).show();
                    }

                    // Code to be executed when the user clicks on an ad.
                    @Override
                    public void onAdClicked() {
                        Toast.makeText(getApplicationContext(), "Ad is clicked!", Toast.LENGTH_SHORT).show();

                    }

                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());
    }

    public void mapUnifiedNativeAdToLayout(UnifiedNativeAd adFromGoogle, UnifiedNativeAdView myAdView) {


        MediaView mediaView = myAdView.findViewById(R.id.ad_media);
        myAdView.setMediaView(mediaView);

        myAdView.setHeadlineView(myAdView.findViewById(R.id.ad_headline));
        myAdView.setBodyView(myAdView.findViewById(R.id.ad_body));
        myAdView.setCallToActionView(myAdView.findViewById(R.id.ad_call_to_action));


        myAdView.setIconView(myAdView.findViewById(R.id.ad_icon));
        myAdView.setPriceView(myAdView.findViewById(R.id.ad_price));
        myAdView.setStoreView(myAdView.findViewById(R.id.ad_store));
        myAdView.setStarRatingView(myAdView.findViewById(R.id.ad_rating));

        myAdView.setAdvertiserView(myAdView.findViewById(R.id.ad_advertiser));

        ((TextView) myAdView.getHeadlineView()).setText(adFromGoogle.getHeadline());

        if (adFromGoogle.getBody() == null) {
            myAdView.getBodyView().setVisibility(View.GONE);
        } else {
            ((TextView) myAdView.getBodyView()).setText(adFromGoogle.getBody());
        }

        if (adFromGoogle.getCallToAction() == null) {
            myAdView.getCallToActionView().setVisibility(View.GONE);
        } else {
            ((Button) myAdView.getCallToActionView()).setText(adFromGoogle.getCallToAction());
        }

        if (adFromGoogle.getIcon() == null) {
            myAdView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) myAdView.getIconView()).setImageDrawable(adFromGoogle.getIcon().getDrawable());
        }

        if (adFromGoogle.getPrice() == null) {
            myAdView.getPriceView().setVisibility(View.GONE);
        } else {
            ((TextView) myAdView.getPriceView()).setText(adFromGoogle.getPrice());
        }
        if (adFromGoogle.getStore() == null) {
            myAdView.getStoreView().setVisibility(View.GONE);
        } else {
            ((TextView) myAdView.getStoreView()).setText(adFromGoogle.getStore());
        }

        if (adFromGoogle.getStarRating() == null) {
            myAdView.getStarRatingView().setVisibility(View.GONE);
        } else {
            ((RatingBar) myAdView.getStarRatingView()).setRating(adFromGoogle.getStarRating().floatValue());
        }

        if (adFromGoogle.getAdvertiser() == null) {
            myAdView.getAdvertiserView().setVisibility(View.GONE);
        } else {
            ((TextView) myAdView.getAdvertiserView()).setText(adFromGoogle.getAdvertiser());
        }

        myAdView.setNativeAd(adFromGoogle);
    }

}
