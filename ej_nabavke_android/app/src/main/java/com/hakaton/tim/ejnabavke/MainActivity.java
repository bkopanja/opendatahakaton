package com.hakaton.tim.ejnabavke;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private Button btnLogin;
    private GoogleApiClient mGoogleApiClient;
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    private TextView mStatusTextView;
    private ProgressDialog mProgressDialog;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    private String apiUrl = "http://10.120.193.137/opendatahakaton/ej_nabavke_web/api/v1/register_user/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setScopes(gso.getScopeArray());

        findViewById(R.id.sign_in_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void onSignInFailed() {
        // TODO Auto-generated method stub
        Toast.makeText(getApplicationContext(), "Logovanje failed", Toast.LENGTH_SHORT).show();
    }

    public void onSignInSucceeded() {
        // TODO Auto-generated method stub
        Toast.makeText(getApplicationContext(), "Logovanje uspelo", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnected(Bundle bundle) {



    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(getApplicationContext(),"Konekcija neuspesna", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(getApplicationContext(),"Konekcija neuspesna", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
           // Toast.makeText(getApplicationContext(), acct.getEmail(), Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(), acct.getDisplayName(), Toast.LENGTH_SHORT).show();

            OkHttpClient client = new OkHttpClient();

            JSONObject joParameters = new JSONObject();
            try {
                joParameters.put("email", acct.getEmail());
                joParameters.put("name", acct.getDisplayName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            RequestBody body = RequestBody.create(JSON, joParameters.toString());
            Request request = new Request.Builder()
                    .url(apiUrl)
                    .addHeader("X-API-KEY", "S5622XKZS9AH658SCYRVCCFVLTUMNJD2TZXCS")
                    .addHeader("Content-Type", "application/json")
                    .post(body)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                String responseString = response.body().string();
                Toast.makeText(getApplicationContext(), responseString, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
//            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
//            startActivity(intent);
//            finish();
        }
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                      //  updateUI(false);
                        // [END_EXCLUDE]
                    }
                });
    }


}
