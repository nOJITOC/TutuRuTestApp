package com.marukhin.tuturuapp.core.di.modules;

import com.marukhin.tuturuapp.root.MainModel;
import com.marukhin.tuturuapp.root.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    MainPresenter presenter;
    @Provides
    MainModel provideMainModel() {
        return new MainModel();
    }

    @Provides
    MainPresenter provideMainPresenter() {
        if(presenter == null)
            presenter = new MainPresenter();
        return presenter;
    }
}
