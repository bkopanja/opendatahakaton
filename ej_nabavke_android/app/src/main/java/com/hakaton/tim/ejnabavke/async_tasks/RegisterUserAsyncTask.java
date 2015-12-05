package com.hakaton.tim.ejnabavke.async_tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;
import org.json.JSONObject;

/**
 * Created by bojankopanja on 12/5/15.
 */
public class RegisterUserAsyncTask extends AsyncTask<String, Void, JSONObject> {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private String apiUrl = "http://10.120.193.137/opendatahakaton/ej_nabavke_web/api/v1/register_user/";

    private DeferredObject<JSONObject, Void, Void> deferredObject;
    private Context context;
    private GoogleSignInAccount account;

    public RegisterUserAsyncTask(Context context, GoogleSignInAccount account) {
        this.context = context;
        this.account = account;
        this.deferredObject = new DeferredObject<>();
    }

    @Override
    protected JSONObject doInBackground(String... params) {

        OkHttpClient client = new OkHttpClient();

        JSONObject joParameters = new JSONObject();
        try {
            joParameters.put("email", account.getEmail());
            joParameters.put("name", account.getDisplayName());

            RequestBody body = RequestBody.create(JSON, joParameters.toString());
            Request request = new Request.Builder()
                .url(apiUrl)
                .addHeader("X-API-KEY", "S5622XKZS9AH658SCYRVCCFVLTUMNJD2TZXCS")
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

            Response response = client.newCall(request).execute();
            String responseString = response.body().string();
            return new JSONObject(responseString);
        } catch (Exception e) {
            e.printStackTrace();
            deferredObject.reject(null);
        }

        return null;

    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        if(jsonObject != null) {
            deferredObject.resolve(jsonObject);
        } else {
            deferredObject.reject(null);
        }
    }

    public Promise<JSONObject, Void, Void> getPromise() {
        return deferredObject.promise();
    }
}
