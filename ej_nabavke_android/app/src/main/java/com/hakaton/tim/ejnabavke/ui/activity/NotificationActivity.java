package com.hakaton.tim.ejnabavke.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.hakaton.tim.ejnabavke.R;
import com.hakaton.tim.ejnabavke.util.Constants;

public class NotificationActivity extends AppCompatActivity {

    private CardView cvMail, cvSMS, cvNotification;
    private CheckBox cbMail, cbSMS, cbNotification;
    private LinearLayout layoutMail, layoutSMS, layoutNotification;
    private boolean settings, mail, notification;

    private SharedPreferences pref;
    private boolean settingsMail, settingsSMS, settingsNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        pref = getSharedPreferences(Constants.PREFS_NAME,0);

        settingsMail = pref.getBoolean("mail", false);
        settingsSMS = pref.getBoolean("sms", false);
        settingsNotification = pref.getBoolean("notification", false);

        layoutMail = (LinearLayout)findViewById(R.id.layoutMail);
        layoutNotification = (LinearLayout)findViewById(R.id.layoutNotification);
        layoutSMS = (LinearLayout)findViewById(R.id.layoutSMS);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        cvMail = (CardView)findViewById(R.id.cvMailNotifications);
        cvSMS = (CardView)findViewById(R.id.cvSmsNotifications);
        cvNotification = (CardView)findViewById(R.id.cvMobileNotifications);

        cbMail = (CheckBox) findViewById(R.id.cbNotificationMail);
        cbSMS = (CheckBox)findViewById(R.id.cbNotificationSMS);
        cbNotification = (CheckBox)findViewById(R.id.cbNotificationMobile);

        if(toolbar != null) {
            setSupportActionBar(toolbar);
        }

        if(settingsMail)
            fillMail();
        if(settingsNotification)
            fillNotification();
        if(settingsSMS)
            fillSMS();

        cbMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbMail.isChecked()) {
                    layoutMail.setBackgroundColor(Color.parseColor("#00BBD3"));
                    settingsMail = true;
                } else {
                    layoutMail.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    settingsMail = false;
                }

            }


        });

        cbNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbNotification.isChecked()) {
                    layoutNotification.setBackgroundColor(Color.parseColor("#00BBD3"));
                    settingsNotification = true;
                } else {
                    layoutNotification.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    settingsNotification = false;
                }
            }
        });

        cbSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbSMS.isChecked()) {
                    layoutSMS.setBackgroundColor(Color.parseColor("#00BBD3"));
                    settingsSMS = true;
                } else {
                    layoutSMS.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    settingsSMS = false;
                }
            }
        });

        layoutSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillSMS();
            }
        });

        layoutNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillNotification();
            }
        });

        layoutMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillMail();
            }
        });

    }

    private void fillSMS() {
        if(!settingsSMS) {
            layoutSMS.setBackgroundColor(Color.parseColor("#00BBD3"));
            settingsSMS = true;
            cbSMS.setChecked(true);
        } else {
            layoutSMS.setBackgroundColor(Color.parseColor("#FFFFFF"));
            settingsSMS = false;
            cbSMS.setChecked(false);
        }
    }

    private void fillNotification() {
        if(!settingsNotification) {
            layoutNotification.setBackgroundColor(Color.parseColor("#00BBD3"));
            settingsNotification=true;
            cbNotification.setChecked(true);
        } else {
            layoutNotification.setBackgroundColor(Color.parseColor("#FFFFFF"));
            settingsNotification = false;
            cbNotification.setChecked(false);
        }
    }

    private  void fillMail() {
        if(!settingsMail) {
            layoutMail.setBackgroundColor(Color.parseColor("#00BBD3"));
            settingsMail = true;
            cbMail.setChecked(true);
        } else {
            layoutMail.setBackgroundColor(Color.parseColor("#FFFFFF"));
            settingsMail = false;
            cbMail.setChecked(false);
        }
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
                commitChanges();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void commitChanges()
    {
        SharedPreferences dms = getSharedPreferences(Constants.PREFS_NAME, 0);
        SharedPreferences.Editor editor = dms.edit();
        try {
            editor.putBoolean("mail", settingsMail);
            editor.putBoolean("sms", settingsSMS);
            editor.putBoolean("notification", settingsNotification);
        } catch (Exception e) {
            e.printStackTrace();
        }
        editor.commit();
    }

}
