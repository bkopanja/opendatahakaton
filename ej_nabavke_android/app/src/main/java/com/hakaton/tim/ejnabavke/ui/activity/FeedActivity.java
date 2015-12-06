package com.hakaton.tim.ejnabavke.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.hakaton.tim.ejnabavke.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FeedActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rvFeed)
    RecyclerView rvFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_filter);
        ButterKnife.bind(this);

        if(toolbar != null) {

            setSupportActionBar(toolbar);

            // Show menu icon
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
//                actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
//                actionBar.setDisplayHomeAsUpEnabled(true);
//                actionBar.setShowHideAnimationEnabled(true);
            }
        }

    }
}
