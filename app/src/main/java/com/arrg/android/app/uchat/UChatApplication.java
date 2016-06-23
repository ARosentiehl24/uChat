package com.arrg.android.app.uchat;

import android.app.Application;

import com.firebase.client.Firebase;

public class UChatApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }
}
