package com.marukhin.tuturuapp.station;

import android.app.Dialog;
import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.marukhin.tuturuapp.R;
import com.marukhin.tuturuapp.core.App;
import com.marukhin.tuturuapp.databinding.FragmentStationBinding;
import com.marukhin.tuturuapp.root.IMainView;

import javax.inject.Inject;


public class StationDialogFragment extends DialogFragment implements IStationView{

    @Inject
    StationPresenter mPresenter;
    private static final String STATION_ID = "STATION_ID";
    private long stationId;

    public static StationDialogFragment newInstance(long stationId) {
        StationDialogFragment fragment = new StationDialogFragment();
        Bundle args = new Bundle();
        args.putLong(STATION_ID, stationId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        stationId = getArguments().getLong(STATION_ID);
        App.getAppComponent().inject(this);
        FragmentStationBinding binding = DataBindingUtil.inflate(getActivity().getLayoutInflater(), R.layout.fragment_station, null, false);
        mPresenter.attachView(this);
        mPresenter.initView();
        mPresenter.setStation(stationId);
        binding.setStation(mPresenter.getStation());
        mPresenter.setStation(stationId);
        return new AlertDialog.Builder(getActivity())
                .setView(binding.getRoot())
                .setPositiveButton(R.string.alert_dialog_ok,
                        (dialog, whichButton) -> ((IMainView) getActivity()).chooseStation(stationId)
                )
                .setNegativeButton(R.string.alert_dialog_cancel,
                        (dialog, whichButton) -> {
                            //noOp
                        }
                )
                .create();
    }
}
