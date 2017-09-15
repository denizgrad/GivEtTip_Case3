package com.example.nermi.gitettip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewUserActivity extends AppCompatActivity {

    EditText newUsername;
    EditText newPassword;
    EditText repeatedPassword;
    EditText newEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        newUsername = (EditText) findViewById(R.id.newUserName);
        newPassword = (EditText) findViewById(R.id.newPassword);
        repeatedPassword = (EditText) findViewById(R.id.repeatedPassword);
        newEmail = (EditText) findViewById(R.id.newEmail);
    }

    public void sendNewUserInfo(View view) {
        if (TextUtils.isEmpty(newUsername.getText().toString()))
            newUsername.setError("Please enter a username");
        else if (TextUtils.isEmpty(newPassword.getText().toString()))
            newPassword.setError("Please enter a password");
        else if (TextUtils.isEmpty(repeatedPassword.getText().toString()))
            repeatedPassword.setError("Please re-enter your password");
        else if (TextUtils.isEmpty(newEmail.getText().toString()))
            newEmail.setError("Please enter a valid mail");
        else{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
