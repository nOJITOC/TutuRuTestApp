package com.marukhin.tuturuapp.core.layers.presenter;

import android.databinding.BaseObservable;
import android.support.annotation.NonNull;

import com.marukhin.tuturuapp.core.layers.view.IView;

import java.lang.ref.WeakReference;


public abstract class Presenter<V extends IView> extends BaseObservable {

    private WeakReference<V> mView;


    public void attachView(@NonNull V view) {
        mView = new WeakReference<V>(view);
    }

    public abstract void initView();

    public V getView() {
        return mView.get();
    }
}
