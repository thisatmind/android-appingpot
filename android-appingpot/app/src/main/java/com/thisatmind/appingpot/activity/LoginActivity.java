package com.thisatmind.appingpot.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.thisatmind.appingpot.R;


public class LoginActivity extends AppCompatActivity {

    ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private Handler progressBarHandler = new Handler();

    private LoginButton facebookLoginBtn;
    private CallbackManager callbackManager;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private static final String ANONYMOUS_USER_TYPE = "anonymous";
    private static final String FACEBOOK_USER_TYPE = "facebook";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    startActivity(new Intent(getApplication(), MainActivity.class));
                    LoginActivity.this.finish();
                } else {
                    // User is signed out
                    LoginManager.getInstance().logOut();
                    Log.d("mAuthListener", "onAuthStateChanged:signed_out");
                }

            }
        };

        callbackManager = CallbackManager.Factory.create();

        facebookLoginBtn = (LoginButton) findViewById(R.id.facebook_login_button);
        facebookLoginBtn.setReadPermissions("email", "public_profile");
        facebookLoginBtn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                progressBar = new ProgressDialog(LoginActivity.this);
                progressBar.setMessage("Facebook login");
                progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressBar.setProgress(50);
                progressBar.setMax(100);
                progressBar.show();

                //reset progress bar status
                progressBarStatus = 50;

                Log.d("LoginActivity", "facebook login success");
                Log.d("facebook token", loginResult.getAccessToken().getToken());
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d("LoginActivity", "facebook login fail");
            }

            @Override
            public void onError(FacebookException exception) {
                Log.d("LoginActivity", "facebook login error");
            }
        });

        Button anonymousLoginBtn = (Button) findViewById(R.id.anonymous_login_button);
        anonymousLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String TAG = "anonymous login";
                mAuth.signInAnonymously()
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "signInAnonymously:onComplete:" + task.isSuccessful());

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Log.w(TAG, "signInAnonymously", task.getException());
                                    return;
                                }
                                startActivity(new Intent(getApplication(), TutorialActivity.class));
                                LoginActivity.this.finish();
                            }
                        });
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        final String TAG = "hdfbAccessToken";
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        progressBarStatus = 100;
                        // Update the progress bar
                        progressBarHandler.post(new Runnable() {
                            public void run() {
                                progressBar.setProgress(progressBarStatus);
                            }
                        });
                        // close the progress bar dialog
                        progressBar.dismiss();

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()){
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }

                        startActivity(new Intent(getApplication(), TutorialActivity.class));
                        LoginActivity.this.finish();

                        // ...
                    }
                });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
