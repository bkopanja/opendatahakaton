package com.hakaton.tim.ejnabavke.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hakaton.tim.ejnabavke.R;

import butterknife.ButterKnife;

/**
 * Created by bojankopanja on 12/5/15.
 */
public class CategoryFilterFragment extends Fragment {

    public static CategoryFilterFragment newInstance() {
        CategoryFilterFragment fragment = new CategoryFilterFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories_filter, container, false);
        ButterKnife.bind(this, view);

        return view;
    }


}
