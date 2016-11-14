package com.marukhin.tuturuapp.core.utils;


import android.databinding.BindingConversion;

import android.view.View;

public class BundingUtils {


    @BindingConversion
    public static int convertConditionToVisibility(final boolean condition) {
        return condition ? View.VISIBLE : View.GONE;
    }
}
