package com.hakaton.tim.ejnabavke;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hakaton.tim.ejnabavke.util.Constants;

import org.apache.commons.lang3.StringUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by KDragan on 5.12.2015.
 */
public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        final String user = getSharedPreferences(Constants.PREFS_NAME, 0).getString("user", "");
        final String email = getSharedPreferences(Constants.PREFS_NAME, 0).getString("email", "");

        Timer introTimer = new Timer();
        introTimer.schedule(new TimerTask() {
            public void run() {
                if(StringUtils.isNotBlank(user) && StringUtils.isNotBlank(email)) {
                    Intent intent = new Intent(IntroActivity.this, FilterActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                    startActivity(intent);
                }

                finish();
            }
        }, 2500);
    }
}
