package com.koko.foodie.Utils;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class Foodie extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }


}
