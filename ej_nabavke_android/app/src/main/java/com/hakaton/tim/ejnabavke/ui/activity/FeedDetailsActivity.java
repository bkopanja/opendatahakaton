package com.hakaton.tim.ejnabavke.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.hakaton.tim.ejnabavke.R;
import com.hakaton.tim.ejnabavke.model.FeedEntity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FeedDetailsActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_details);
        ButterKnife.bind(this);

        FeedEntity feed = (FeedEntity) getIntent().getSerializableExtra("feedDetail");
        if(feed != null) {
            if (toolbar != null) {

                setSupportActionBar(toolbar);
                setTitle(feed.getNaziv_dokumenta());

                // Show menu icon
                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setHomeAsUpIndicator(R.mipmap.ic_back);
                    actionBar.setDisplayHomeAsUpEnabled(true);
                    actionBar.setShowHideAnimationEnabled(true);
                }
            }

            setFeedUI();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setFeedUI() {

    }

}
