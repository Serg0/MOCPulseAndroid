package com.masterofcode.pulse.ui;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.masterofcode.pulse.R;

import butterknife.InjectView;

/**
 * Created by Serhii Nadolynskyi <serhii.nadolinskyi@gmail.com> on 04.07.15.
 */
public class BaseActivity extends ActionBarActivity {


    public void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void showToastUnderConstruction() {
        showToast("This feature is under construction");
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

    private void initActionBar() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.app_name);
//        actionBar.setSubtitle(label);
        actionBar.setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationIcon(R.drawable.ic_nav_back);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//        toolbar.setOnMenuItemClickListener(
//                new Toolbar.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        switch (item.getItemId()) {
//                            case R.id.it_done: {
//                                submit();
//                                break;
//                            }
//                            default: {
//                                break;
//                            }
//                        }
//                        return true;
//                    }
//                });

    }





}
