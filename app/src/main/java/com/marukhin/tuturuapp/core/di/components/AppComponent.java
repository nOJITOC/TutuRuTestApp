package com.marukhin.tuturuapp.core.di.components;

import android.content.Context;


import com.marukhin.tuturuapp.core.di.modules.AppModule;
import com.marukhin.tuturuapp.core.di.modules.LocalModule;
import com.marukhin.tuturuapp.core.di.modules.MainModule;
import com.marukhin.tuturuapp.core.di.modules.ModelModule;
import com.marukhin.tuturuapp.core.di.modules.NetworkModule;
import com.marukhin.tuturuapp.core.di.modules.StationModule;
import com.marukhin.tuturuapp.core.layers.model.Model;
import com.marukhin.tuturuapp.core.managers.DataManager;
import com.marukhin.tuturuapp.root.MainActivity;
import com.marukhin.tuturuapp.root.MainPresenter;
import com.marukhin.tuturuapp.station.StationDialogFragment;
import com.marukhin.tuturuapp.station.StationPresenter;

import dagger.Component;


@Component(modules = {
        AppModule.class,
        LocalModule.class,
        ModelModule.class,
        NetworkModule.class,
        MainModule.class,
        StationModule.class,
})
public interface AppComponent {
    Context getContext();
    void inject(DataManager dataManager);
    void inject(Model model);

    void inject(MainPresenter presenter);
    void inject(MainActivity activity);

    void inject(StationPresenter stationPresenter);
    void inject(StationDialogFragment fragment);
}
