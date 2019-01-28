package com.serhiiv.test.currate.ui.main;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.serhiiv.test.currate.R;
import com.serhiiv.test.currate.core.base.BaseActivity;
import com.serhiiv.test.currate.ui.info.InfoActivity;
import com.serhiiv.test.currate.ui.main.viewmodel.MainViewModel;

import javax.inject.Inject;


public class MainActivity extends BaseActivity {

    @Inject
    MainFragment injectedFragment;
    @Inject
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.info_button).setOnClickListener(view -> viewModel.prepareInfo());

        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, injectedFragment)
                    .commit();
        }

        viewModel.showInfo().observe(this, aVoid -> {
            startActivity(InfoActivity.startIntent(this));
        });
    }

}
