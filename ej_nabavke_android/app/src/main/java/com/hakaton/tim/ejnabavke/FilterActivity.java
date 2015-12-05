package com.hakaton.tim.ejnabavke;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;

public class FilterActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        if (toolbar != null) {

            toolbar.setTitle("Filtriranje");
            setSupportActionBar(toolbar);

            // Show menu icon
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
//                actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setShowHideAnimationEnabled(true);
            }
        }


    }
}
