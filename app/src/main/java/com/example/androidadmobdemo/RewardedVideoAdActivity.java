package com.example.androidadmobdemo;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import androidx.appcompat.app.AppCompatActivity;

public class RewardedVideoAdActivity extends AppCompatActivity {

    RewardedVideoAd mRewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewarded_ad);

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917", adRequest);

        mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {

            //This method is executed when an ad has finished loading
            @Override
            public void onRewardedVideoAdLoaded() {
                Toast.makeText(RewardedVideoAdActivity.this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();

                showRewardedVideoAd();
            }

            //method is invoked when the user should be rewarded for interacting with the ad
            @Override
            public void onRewarded(RewardItem rewardItem) {
                Toast.makeText(RewardedVideoAdActivity.this, "onRewarded! currency: " + rewardItem.getType() + "  amount: " + rewardItem.getAmount(), Toast.LENGTH_SHORT).show();

            }

            //called after onAdOpened(), when a user click opens another app, backgrounding the current app
            @Override
            public void onRewardedVideoAdLeftApplication() {
                Toast.makeText(RewardedVideoAdActivity.this, "onRewardedVideoAdLeftApplication",
                        Toast.LENGTH_SHORT).show();
            }

            //method is invoked when the rewarded ad is closed
            // due to the user tapping on the close icon or using the back button.
            @Override
            public void onRewardedVideoAdClosed() {
                Toast.makeText(RewardedVideoAdActivity.this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
            }

            //method is invoked when an ad fails to load
            @Override
            public void onRewardedVideoAdFailedToLoad(int errorCode) {
                Toast.makeText(RewardedVideoAdActivity.this, "onRewardedVideoAdFailedToLoad" + errorCode, Toast.LENGTH_SHORT).show();
            }

            //method is invoked when the ad is displayed, covering the device's screen.
            @Override
            public void onRewardedVideoAdOpened() {
                Toast.makeText(RewardedVideoAdActivity.this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
            }

            //method is invoked when rewarded video started
            @Override
            public void onRewardedVideoStarted() {
                Toast.makeText(RewardedVideoAdActivity.this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
            }

            //method is invoked when rewarded video completed
            @Override
            public void onRewardedVideoCompleted() {
                Toast.makeText(RewardedVideoAdActivity.this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showRewardedVideoAd() {
        if (mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }
    }


}
