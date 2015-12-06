package com.hakaton.tim.ejnabavke.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import com.hakaton.tim.ejnabavke.R;

public class NotificationActivity extends AppCompatActivity {

    private CardView cvMail, cvSMS, cvNotification;
    private CheckBox cbMail, cbSMS, cbNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if(toolbar != null) {
            setSupportActionBar(toolbar);
        }

        cvMail = (CardView)findViewById(R.id.cvMailNotifications);
        cvSMS = (CardView)findViewById(R.id.cvSmsNotifications);
        cvNotification = (CardView)findViewById(R.id.cvMobileNotifications);

        cbMail = (CheckBox) findViewById(R.id.cbNotificationMail);
        cbSMS = (CheckBox)findViewById(R.id.cbNotificationSMS);
        cbNotification = (CheckBox)findViewById(R.id.cbNotificationMobile);

        cvMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbMail.isChecked()) {
                    cvMail.setBackgroundColor(Color.parseColor("#00BBD3"));
                } else {
                    cvMail.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }
                if(cbSMS.isChecked()) {
                    cbSMS.setBackgroundColor(Color.parseColor("#00BBD3"));
                } else {
                    cbSMS.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }
                if(cbNotification.isChecked()) {
                    cbNotification.setBackgroundColor(Color.parseColor("#00BBD3"));
                } else {
                    cbNotification.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }
            }

        });

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
                Intent next = new Intent(this, FeedActivity.class);
                startActivity(next);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
