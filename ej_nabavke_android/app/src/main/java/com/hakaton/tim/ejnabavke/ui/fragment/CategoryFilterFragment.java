package com.hakaton.tim.ejnabavke.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hakaton.tim.ejnabavke.R;
import com.hakaton.tim.ejnabavke.adapters.CategoryAdapter;
import com.hakaton.tim.ejnabavke.async_tasks.GetCategoriesAsyncTask;
import com.hakaton.tim.ejnabavke.model.CardViewItemInterface;
import com.hakaton.tim.ejnabavke.model.CategoryEntity;

import org.jdeferred.AlwaysCallback;
import org.jdeferred.DoneCallback;
import org.jdeferred.Promise;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by bojankopanja on 12/5/15.
 */
public class CategoryFilterFragment extends Fragment implements CardViewItemInterface {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.pbLoadingProgress)
    ProgressBar pbLoadingProgress;

    private List<CategoryEntity> categoryEntities = new ArrayList<>();
    private CategoryAdapter categoryAdapter = null;

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

        GetCategoriesAsyncTask getCategoriesAsyncTask = new GetCategoriesAsyncTask(getActivity());
        Promise<JSONObject, Void, Void> promise = getCategoriesAsyncTask.getPromise();
        getCategoriesAsyncTask.execute();

        pbLoadingProgress.setVisibility(View.VISIBLE);
        pbLoadingProgress.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getActivity(), R.color.my_primary), android.graphics.PorterDuff.Mode.MULTIPLY);

        promise.done(new DoneCallback<JSONObject>() {
            @Override
            public void onDone(JSONObject result) {
                try {
                    JSONArray categories = result.getJSONArray("kategorije");
                    Gson gsonBuilder = new GsonBuilder().create();
                    categoryEntities = new ArrayList<>();

                    for(int i = 0 ; i < categories.length(); i++) {
                        String town = categories.getJSONObject(i).toString();
                        categoryEntities.add(gsonBuilder.fromJson(town, CategoryEntity.class));
                    }

                    categoryAdapter = new CategoryAdapter(getActivity(), categoryEntities, CategoryFilterFragment.this);
                    recyclerView.setAdapter(categoryAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        })
                .always(new AlwaysCallback<JSONObject, Void>() {
                    @Override
                    public void onAlways(Promise.State state, JSONObject resolved, Void rejected) {
                        pbLoadingProgress.setVisibility(View.GONE);
                    }
                });

        return view;
    }

    public void OnClick(int position) {
        categoryEntities.get(position).setSelected(!categoryEntities.get(position).getSelected());
        categoryAdapter.setData(categoryEntities);
    }

}
