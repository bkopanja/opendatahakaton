package com.hakaton.tim.ejnabavke;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONObject;

public class MestaActivity extends AppCompatActivity {

    private String url = "http://10.120.193.137/opendatahakaton/ej_nabavke_web/api/v1/list_cities";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesta);

        JSONObject json = new JSONObject();


    }
}
