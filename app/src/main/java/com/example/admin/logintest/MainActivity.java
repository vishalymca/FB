package com.example.admin.logintest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity{

    LoginButton loginButton;
    TextView textView;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView( R.layout.activity_main );
        loginButton = findViewById( R.id.login_button );
        textView=findViewById( R.id.text_view);

        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback( callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                textView.setText("Login Success \n" +
                loginResult.getAccessToken().getUserId()+
                        "\n" +loginResult.getAccessToken().getToken());
            }

            @Override
            public void onCancel() {
                textView.setText( "Login Cancelled" );
            }

            @Override
            public void onError(FacebookException exception) {

            }

        } );
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
