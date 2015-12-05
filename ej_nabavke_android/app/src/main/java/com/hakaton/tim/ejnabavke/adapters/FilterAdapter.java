package com.hakaton.tim.ejnabavke.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hakaton.tim.ejnabavke.R;
import com.hakaton.tim.ejnabavke.ui.fragment.CategoryFilterFragment;
import com.hakaton.tim.ejnabavke.ui.fragment.TownsFilterFragment;

/**
 * Created by bojankopanja on 12/5/15.
 */
public class FilterAdapter extends FragmentPagerAdapter {

    private Context context;

    public FilterAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return CategoryFilterFragment.newInstance();
            case 0:
            default:
                return TownsFilterFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 1: return context.getString(R.string.titleKategorije);
            case 0:
            default:
                return context.getString(R.string.titleMesta);
        }
    }
}
