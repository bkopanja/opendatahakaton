package com.hakaton.tim.ejnabavke.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.hakaton.tim.ejnabavke.R;
import com.hakaton.tim.ejnabavke.adapters.FilterAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FilterActivity extends AppCompatActivity {

    @Bind(R.id.tabLayout)
    TabLayout tabLayout;

    @Bind(R.id.pager)
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);

        mPager.setAdapter(new FilterAdapter(getSupportFragmentManager(), this));
        tabLayout.setupWithViewPager(mPager);

    }
}
