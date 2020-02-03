package com.example.androidadmobdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private AdView mAdView;
    Button btnInterstitialAd, btnNativeAd, btnRewardedAd, btnBannerAd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAdView = findViewById(R.id.adView);

        btnInterstitialAd = findViewById(R.id.btn_interstitial_ad);
        btnNativeAd = findViewById(R.id.btn_native_ad);
        btnRewardedAd = findViewById(R.id.btn_rewarded_ad);
        btnBannerAd = findViewById(R.id.btn_banner_ad);

        btnInterstitialAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, InterstitialAdActivity.class));
            }
        });
        btnNativeAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NativeAdActivity.class));
            }
        });
        btnRewardedAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RewardedVideoAdActivity.class));
            }
        });
        btnBannerAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdRequest adRequest = new AdRequest.Builder()
                        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                        .build();

                mAdView.loadAd(adRequest);
            }
        });


        mAdView.setAdListener(new AdListener() {

            // Code to be executed when an ad finishes loading.
            @Override
            public void onAdLoaded() {
                Toast.makeText(getApplicationContext(), "Ad loaded!", Toast.LENGTH_SHORT).show();
            }

            //Code to be executed when an ad opens an overlay that
            // covers the screen.
            @Override
            public void onAdOpened() {
                Toast.makeText(getApplicationContext(), "Ad left opened!", Toast.LENGTH_SHORT).show();

            }

            // Code to be executed when an ad request fails.
            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(getApplicationContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }

            //called after onAdOpened(), when a user click opens another app (such as the Google Play), backgrounding the current app
            @Override
            public void onAdLeftApplication() {
                Toast.makeText(getApplicationContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
            }

            // Code to be executed when the user clicks on an ad.
            @Override
            public void onAdClicked() {
                Toast.makeText(getApplicationContext(), "Ad is clicked!", Toast.LENGTH_SHORT).show();
            }

            // Code to be executed when the user is about to return
            // to the app after tapping on an ad.
            @Override
            public void onAdClosed() {
                Toast.makeText(getApplicationContext(), "Ad is closed!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}

