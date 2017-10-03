package com.example.nermi.gitettip;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import models.User;
import utilities.ApiLogin;
import utilities.SharedPreferencesUtility;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkLocationPermissions();

        // Check if user is already logged in.
//        SharedPreferencesUtility.removeValue(this, "userId");
        int userId = SharedPreferencesUtility.readValue(this, "userId");
        if (userId > 0) {
            Intent intent = new Intent(this, UserActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            this.startActivity(intent);
        }

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
    }

    public void createNewUser(View view) {
            Intent intent = new Intent(this, NewUserActivity.class);
            startActivity(intent);
    }

    public void logIn(View view) {
        if(TextUtils.isEmpty(username.getText().toString()))
            username.setError("Please enter your username.");
        else if(TextUtils.isEmpty(password.getText().toString()))
            password.setError("Please enter your password.");
        else{
            User u = new User(username.getText().toString(), password.getText().toString());
            new ApiLogin(this, true).execute(u);
        }
    }

    public void checkLocationPermissions(){
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    123);

            return;
        }
    }
}
