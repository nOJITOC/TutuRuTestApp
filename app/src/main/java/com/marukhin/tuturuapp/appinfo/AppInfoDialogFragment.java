package com.marukhin.tuturuapp.appinfo;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.marukhin.tuturuapp.BuildConfig;
import com.marukhin.tuturuapp.R;
import com.marukhin.tuturuapp.databinding.FragmentAppInfoBinding;


public class AppInfoDialogFragment extends DialogFragment {
    private static char DIVIDER = '\n';
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        PackageManager packageManager = getActivity().getBaseContext().getPackageManager();
        ApplicationInfo info  = getActivity().getBaseContext().getApplicationInfo();
        FragmentAppInfoBinding binding = DataBindingUtil.inflate(getActivity().getLayoutInflater(), R.layout.fragment_app_info, null, false);
        binding.aboutIcon.setImageDrawable(info.loadIcon(packageManager));
        String appInfo = new StringBuilder()
                .append(getString(R.string.copyright))
                .append(getString(R.string.developer_name))
                .append(DIVIDER)
                .append(getString(R.string.info_app_name_description))
                .append(info.loadLabel(packageManager))
                .append(DIVIDER)
                .append(getString(R.string.version_description))
                .append(BuildConfig.VERSION_NAME)
                .append(DIVIDER)
                .append(getString(R.string.build_type_description))
                .append(BuildConfig.BUILD_TYPE)
                .toString();
        binding.setAppInfo(appInfo);

        return new AlertDialog.Builder(getActivity())
                .setView(binding.getRoot())
                .setPositiveButton(getString(R.string.app_info_dialog_ok), null)
                .create();
    }
}
