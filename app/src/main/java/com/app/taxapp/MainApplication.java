package com.app.taxapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

public class MainApplication extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }

}
