package com.hakaton.tim.ejnabavke.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.hakaton.tim.ejnabavke.R;
import com.hakaton.tim.ejnabavke.model.FeedEntity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FeedDetailsActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tvDatum)
    TextView tvDatum;
    @Bind(R.id.tvTitle)
    TextView tvTitle;

    @Bind(R.id.tvUgovor)
    TextView tvUgovor;
    @Bind(R.id.tvObustava)
    TextView tvObustava;
    @Bind(R.id.tvPregovaracki)
    TextView tvPregovaracki;
    @Bind(R.id.tvPrava)
    TextView tvPrava;

    private FeedEntity feed = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_details);
        ButterKnife.bind(this);

        feed = (FeedEntity) getIntent().getSerializableExtra("feedDetail");
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
        if(feed != null) {

            tvDatum.setText(feed.getFormattedDate());
            tvTitle.setText(feed.getNaziv_dokumenta());

            tvUgovor.setText(feed.getObavestenje_o_zakljucenom_ugovoru() > 0 ? "DA" : "NE");
            tvObustava.setText(feed.getObavestenje_o_obustavi_postupka_javne_nabavke() > 0 ? "DA" : "NE");
            tvPregovaracki.setText(feed.getPregovaracki_bez_ponuda() > 0 ? "DA" : "NE");
            tvPrava.setText(feed.getObavestenje_o_zastiti_prava() > 0 ? "DA" : "NE");

        }

    }

}
