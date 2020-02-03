package com.example.androidadmobdemo;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //initializes Mobile Ads SDK
        MobileAds.initialize(this, "ca-app-pub-9620678622149713~7105710424");
    }
}
