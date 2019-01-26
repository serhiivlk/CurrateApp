package com.serhiiv.test.currate.ui.info;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.serhiiv.test.currate.R;
import com.serhiiv.test.currate.core.base.BaseActivity;
import com.serhiiv.test.currate.ui.info.viewmodel.InfoViewModel;

import javax.inject.Inject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

public class InfoActivity extends BaseActivity {

    @Inject
    InfoFragment injectedFragment;
    @Inject
    InfoViewModel viewModel;

    public static Intent startIntent(Context context) {
        return new Intent(context, InfoActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, injectedFragment)
                    .commit();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
