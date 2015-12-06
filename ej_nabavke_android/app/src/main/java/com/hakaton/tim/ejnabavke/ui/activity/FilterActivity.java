package com.hakaton.tim.ejnabavke.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.hakaton.tim.ejnabavke.R;
import com.hakaton.tim.ejnabavke.adapters.FilterAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FilterActivity extends AppCompatActivity {

    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.pager)
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);

        if(toolbar != null) {

            setSupportActionBar(toolbar);

//            // Show menu icon
//            ActionBar actionBar = getSupportActionBar();
//            if (actionBar != null) {
//                actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
//                actionBar.setDisplayHomeAsUpEnabled(true);
//                actionBar.setShowHideAnimationEnabled(true);
//            }
        }

        mPager.setAdapter(new FilterAdapter(getSupportFragmentManager(), this));
        tabLayout.setupWithViewPager(mPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionNext:
                Intent next = new Intent(this, NotificationActivity.class);
                startActivity(next);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
