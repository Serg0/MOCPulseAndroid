package com.masterofcode.pulse.ui;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.masterofcode.pulse.R;

/**
 * Created by Serhii Nadolynskyi <serhii.nadolinskyi@gmail.com> on 04.07.15.
 */
public class BaseActivity extends ActionBarActivity {


    public void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    private ProgressDialog dialog;

    public void showProgress(boolean doShow) {

        if (doShow) {
            if (dialog != null) {
                dialog.dismiss();
            }

            dialog = new ProgressDialog(this);
            dialog.setIndeterminate(true);
            dialog.setCancelable(false);
            dialog.show();
            dialog.setContentView(R.layout.dialog_progress);

        } else {
            if (dialog != null) {
                dialog.dismiss();
            }
            dialog = null;
        }

    }





}
