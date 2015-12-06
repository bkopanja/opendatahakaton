package com.hakaton.tim.ejnabavke.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hakaton.tim.ejnabavke.R;
import com.hakaton.tim.ejnabavke.adapters.TownsAdapter;
import com.hakaton.tim.ejnabavke.async_tasks.GetTownsAsyncTask;

import org.jdeferred.DoneCallback;
import org.jdeferred.Promise;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by bojankopanja on 12/5/15.
 */
public class NotificationFilterFragment extends Fragment {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    public static NotificationFilterFragment newInstance() {
        NotificationFilterFragment fragment = new NotificationFilterFragment();
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
        View view = inflater.inflate(R.layout.fragment_notification_filter, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        GetTownsAsyncTask getTownsAsyncTask = new GetTownsAsyncTask(getActivity());
        Promise<JSONObject, Void, Void> promise = getTownsAsyncTask.getPromise();
        getTownsAsyncTask.execute();

        promise.done(new DoneCallback<JSONObject>() {
            @Override
            public void onDone(JSONObject result) {
                try {
                    JSONArray towns = result.getJSONArray("tagovi");
                    TownsAdapter adapter = new TownsAdapter(getActivity(), towns);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


        return view;
    }


}
