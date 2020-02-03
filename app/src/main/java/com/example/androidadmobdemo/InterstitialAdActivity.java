package com.example.androidadmobdemo;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import androidx.appcompat.app.AppCompatActivity;

public class InterstitialAdActivity extends AppCompatActivity {

    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial_ad);


        mInterstitialAd = new InterstitialAd(this);

        // setting ad unit ID
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {

            // Code to be executed when an ad finishes loading.
            public void onAdLoaded() {
                showInterstitial();
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

            //Code to be executed when an ad opens an overlay that
            // covers the screen.
            @Override
            public void onAdOpened() {
                Toast.makeText(getApplicationContext(), "Ad is opened!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showInterstitial() {

        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
            //play game
        }
    }

}
