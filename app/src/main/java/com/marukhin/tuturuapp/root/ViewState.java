package com.marukhin.tuturuapp.root;


import android.support.annotation.IntDef;

@IntDef({
        ViewState.IDLE,
        ViewState.FROM,
        ViewState.TO
})
public @interface ViewState {
    int IDLE = 0;
    int FROM = 1;
    int TO = 2;
}
