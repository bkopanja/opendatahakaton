package com.hakaton.tim.ejnabavke;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.hakaton.tim.ejnabavke.async_tasks.RegisterUserAsyncTask;

import org.jdeferred.DoneCallback;
import org.jdeferred.Promise;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private Button btnLogin;
    private GoogleApiClient mGoogleApiClient;
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    private TextView mStatusTextView;
    private ProgressDialog mProgressDialog;
    private Context mAppContext;
    public static final String PREFS_NAME = "ejnabavke";
    private SharedPreferences pref;
    private GoogleSignInAccount acct;
    private String user="";
    private String email="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences(PREFS_NAME,0);
        user = pref.getString("user",user);
        email = pref.getString("email",email);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        Button signInButton = (Button) findViewById(R.id.sign_in_button);

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
            acct = result.getSignInAccount();

            RegisterUserAsyncTask registerUserAsyncTask = new RegisterUserAsyncTask(this, acct);
            Promise<JSONObject, Void, Void> promise = registerUserAsyncTask.getPromise();
            registerUserAsyncTask.execute();

            promise.done(new DoneCallback<JSONObject>() {
                @Override
                public void onDone(JSONObject result) {
                    try {
                        user = pref.getString("user", acct.getDisplayName());
                        email = pref.getString("name", acct.getEmail());
                        Intent intent = new Intent(MainActivity.this, FilterActivity.class);
                        commitChanges();
                        startActivity(intent);
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


        }
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {

                    }
                });
    }

    private void commitChanges()
    {
        SharedPreferences dms = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = dms.edit();
        try {
            editor.putString("user", acct.getDisplayName());
            editor.putString("email", acct.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
        editor.commit();
    }


}