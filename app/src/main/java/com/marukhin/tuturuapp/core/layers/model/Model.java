package com.marukhin.tuturuapp.core.layers.model;


import com.marukhin.tuturuapp.core.App;
import com.marukhin.tuturuapp.core.managers.DataManager;

import javax.inject.Inject;


public abstract class Model {
    @Inject
    protected DataManager mDataManager;

    public Model() {
        App.getAppComponent().inject(this);
    }

}
