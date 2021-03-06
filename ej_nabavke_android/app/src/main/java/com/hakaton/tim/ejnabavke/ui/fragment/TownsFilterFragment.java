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
import com.hakaton.tim.ejnabavke.adapters.TownsAdapter;
import com.hakaton.tim.ejnabavke.async_tasks.GetTownsAsyncTask;
import com.hakaton.tim.ejnabavke.model.CardViewItemInterface;
import com.hakaton.tim.ejnabavke.model.TownEntity;

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
public class TownsFilterFragment extends Fragment implements CardViewItemInterface {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.pbLoadingProgress)
    ProgressBar pbLoadingProgress;

    private List<TownEntity> townEntities = new ArrayList<>();
    private TownsAdapter townsAdapter = null;

    public static TownsFilterFragment newInstance() {
        TownsFilterFragment fragment = new TownsFilterFragment();
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
        View view = inflater.inflate(R.layout.fragment_towns_filter, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        GetTownsAsyncTask getTownsAsyncTask = new GetTownsAsyncTask(getActivity());
        Promise<JSONObject, Void, Void> promise = getTownsAsyncTask.getPromise();
        getTownsAsyncTask.execute();

        pbLoadingProgress.setVisibility(View.VISIBLE);
        pbLoadingProgress.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getActivity(), R.color.my_primary), android.graphics.PorterDuff.Mode.MULTIPLY);

        promise.done(new DoneCallback<JSONObject>() {
            @Override
            public void onDone(JSONObject result) {
                try {
                    JSONArray towns = result.getJSONArray("mesta");
                    Gson gsonBuilder = new GsonBuilder().create();
                    townEntities = new ArrayList<>();

                    for(int i = 0 ; i < towns.length(); i++) {
                        String town = towns.getJSONObject(i).toString();
                        townEntities.add(gsonBuilder.fromJson(town, TownEntity.class));
                    }


                    townsAdapter = new TownsAdapter(getActivity(), townEntities, TownsFilterFragment.this);
                    recyclerView.setAdapter(townsAdapter);

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


    @Override
    public void OnClick(int position) {
        townEntities.get(position).setSelected(!townEntities.get(position).getSelected());
        townsAdapter.setData(townEntities);
    }
}
