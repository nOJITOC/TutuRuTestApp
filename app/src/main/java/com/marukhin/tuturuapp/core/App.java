package com.marukhin.tuturuapp.core;

import android.app.Application;

import com.marukhin.tuturuapp.core.di.components.AppComponent;
import com.marukhin.tuturuapp.core.di.components.DaggerAppComponent;
import com.marukhin.tuturuapp.core.di.modules.AppModule;

public class App extends Application {

    private static AppComponent sAppComponent;

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        createComponent();
    }

    private void createComponent() {
        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}