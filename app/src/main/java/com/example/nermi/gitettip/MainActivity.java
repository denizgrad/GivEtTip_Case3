package com.example.nermi.gitettip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import models.User;
import utilities.ApiLogin;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            boolean authorized = ApiLogin.checkCredentials(u);
            if (authorized) {
                Intent intent = new Intent(this, UserActivity.class);
                startActivity(intent);
            } else {
                username.setError("The login credentials are not correct.");
            }
        }
    }
}
