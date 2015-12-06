package com.hakaton.tim.ejnabavke.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.CheckBox;

import com.hakaton.tim.ejnabavke.R;
import com.hakaton.tim.ejnabavke.adapters.FilterAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NotificationActivity extends AppCompatActivity {

    @Bind(R.id.pager)
    ViewPager mPager;
    private CardView cvMail, cvSMS, cvNotification;
    private CheckBox cbMail, cbSMS, cbNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        //ButterKnife.bind(this);

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
}
