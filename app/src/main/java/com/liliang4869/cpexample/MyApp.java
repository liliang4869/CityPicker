package com.liliang4869.cpexample;

import android.app.Application;

import com.liliang4869.citypicker.db.CityPicker;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CityPicker.getInstance().init(this);
    }
}
