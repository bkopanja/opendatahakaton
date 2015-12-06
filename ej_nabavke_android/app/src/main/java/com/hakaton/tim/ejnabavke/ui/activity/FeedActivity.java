package com.hakaton.tim.ejnabavke.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hakaton.tim.ejnabavke.R;
import com.hakaton.tim.ejnabavke.adapters.FeedAdapter;
import com.hakaton.tim.ejnabavke.async_tasks.GetFeedsAsyncTask;
import com.hakaton.tim.ejnabavke.model.CardViewItemInterface;
import com.hakaton.tim.ejnabavke.model.FeedEntity;

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

public class FeedActivity extends AppCompatActivity implements CardViewItemInterface {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rvFeed)
    RecyclerView rvFeed;
    @Bind(R.id.pbFeedProgress)
    ProgressBar pbFeedProgress;

    private List<FeedEntity> feedEntities = new ArrayList<>();
    private FeedAdapter feedAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
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


        pbFeedProgress.setVisibility(View.VISIBLE);
        pbFeedProgress.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(this, R.color.my_primary), android.graphics.PorterDuff.Mode.MULTIPLY);

        rvFeed.setLayoutManager(new LinearLayoutManager(this));

        GetFeedsAsyncTask getFeedsAsyncTask = new GetFeedsAsyncTask(this);
        Promise<JSONObject, Void, Void> promise = getFeedsAsyncTask.getPromise();
        getFeedsAsyncTask.execute();

        promise.done(new DoneCallback<JSONObject>() {
            @Override
            public void onDone(JSONObject result) {
                try {
                    JSONArray feed = result.getJSONArray("feed");
                    Gson gsonBuilder = new GsonBuilder().create();

                    for(int i = 0 ; i < feed.length(); i++) {
                        String feedItemString = feed.getJSONObject(i).toString();
                        feedEntities.add(gsonBuilder.fromJson(feedItemString, FeedEntity.class));
                    }

                    feedAdapter = new FeedAdapter(FeedActivity.this, feedEntities, FeedActivity.this);
                    rvFeed.setAdapter(feedAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        })
        .always(new AlwaysCallback<JSONObject, Void>() {
            @Override
            public void onAlways(Promise.State state, JSONObject resolved, Void rejected) {
                pbFeedProgress.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void OnClick(int position) {

        Intent in = new Intent(this, FeedDetailsActivity.class);
        in.putExtra("feedDetail", feedEntities.get(position));
        startActivity(in);

    }
}
