package com.hakaton.tim.ejnabavke.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.hakaton.tim.ejnabavke.R;
import com.hakaton.tim.ejnabavke.adapters.CategoryAdapter;
import com.hakaton.tim.ejnabavke.adapters.TownsAdapter;
import com.hakaton.tim.ejnabavke.model.CategoryEntity;
import com.hakaton.tim.ejnabavke.model.TownEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by bojankopanja on 12/5/15.
 */
public class CategoryFilterFragment extends Fragment {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.pbLoadingProgress)
    ProgressBar pbLoadingProgress;

    private List<CategoryEntity> townEntities = new ArrayList<>();
    private CategoryAdapter townsAdapter = null;

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

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }


}
