package com.marukhin.tuturuapp.root;

import android.support.v4.app.DialogFragment;
import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.marukhin.tuturuapp.R;
import com.marukhin.tuturuapp.appinfo.AppInfoDialogFragment;
import com.marukhin.tuturuapp.core.App;
import com.marukhin.tuturuapp.core.db.models.City;
import com.marukhin.tuturuapp.databinding.ActivityMainBinding;
import com.marukhin.tuturuapp.date.DatePickerFragment;
import com.marukhin.tuturuapp.station.StationDialogFragment;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity
        implements IMainView, NavigationView.OnNavigationItemSelectedListener {

    @Inject
    MainPresenter mPresenter;
    ActivityMainBinding mBinding;
    private Handler mHandler;
    private CitiesAdapter mAdapter;
    protected ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        App.getAppComponent().inject(this);

        mPresenter.attachView(this);
        mBinding.setPresenter(mPresenter);

        prepareProgressDialog();
        initStationList();
        initToolbar();
        initDrawer();

        mPresenter.initView();


    }

    private void initStationList() {
        mAdapter = new CitiesAdapter(this, mPresenter.getStationClickListener());
        mBinding.stationsView.setAdapter(mAdapter);
        mBinding.stationsView.setOnChildClickListener(mAdapter.getChildClickListener());
    }


    private void initToolbar() {
        setSupportActionBar(mBinding.toolbar);
    }

    private void initDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mBinding.drawerLayout, mBinding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mBinding.navView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {

        if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        } else if (!mPresenter.isIdleViewState()) {
            mPresenter.setIdleState();
            mPresenter.freeCities();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_schedule) {
        } else if (id == R.id.nav_about) {
            AppInfoDialogFragment fragment= new AppInfoDialogFragment();
            fragment.show(getFragmentManager(),null);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //region =========================IMainView==============================


    @Override
    public void chooseStation(Long stationId) {
        if (mPresenter.isFromViewState())
            mBinding.fromEt.setText(mPresenter.getStationTitle(stationId));
        if (mPresenter.isToViewState())
            mBinding.toEt.setText(mPresenter.getStationTitle(stationId));
        mPresenter.setIdleState();
        mPresenter.freeCities();
    }

    @Override
    public void chooseDate(Date date) {

        mBinding.dateEt.setText(mPresenter.convertDateToFormatedString(date));
    }

    @Override
    public void showCities(List<City> cities) {
        if (mAdapter != null)
            mAdapter.setCities(cities);
    }

    @Override
    public void showStation(long stationId) {
        StationDialogFragment fragment = StationDialogFragment.newInstance(stationId);
        fragment.show(getFragmentManager(), null);
    }

    @Override
    public void showDatePicker() {
        DialogFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(),null);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showLoad() {
        if (mProgressDialog != null)
            mProgressDialog.show();

    }

    @Override
    public void updateLoad(Integer currentCityCount, Integer cityCount) {
        int progress = (int) ((double) currentCityCount / cityCount * 100);
        Message msg = mHandler.obtainMessage();
        msg.arg1 = progress;
        mHandler.sendMessage(msg);
    }

    //endregion
    private void prepareProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setMessage(getString(R.string.progress_dialog_text));
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                int totalProgress = msg.arg1;
                mProgressDialog.setProgress(totalProgress);
                if (totalProgress >= 100)
                    mProgressDialog.hide();
                super.handleMessage(msg);
            }
        };
    }
}
