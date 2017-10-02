package com.example.nermi.gitettip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import models.User;
import utilities.ApiLogin;

public class NewUserActivity extends AppCompatActivity {

    EditText newName;
    EditText newSurname;
    EditText newEmail;
    EditText newPassword;
    EditText repeatedPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        newName = (EditText) findViewById(R.id.newName);
        newSurname = (EditText) findViewById(R.id.newSurname);
        newEmail = (EditText) findViewById(R.id.newEmail);
        newPassword = (EditText) findViewById(R.id.newPassword);
        repeatedPassword = (EditText) findViewById(R.id.repeatedPassword);
    }

    public void sendNewUserInfo(View view) {
        if (TextUtils.isEmpty(newName.getText().toString()))
            newName.setError("Please enter your name.");
        else if (TextUtils.isEmpty(newSurname.getText().toString()))
            newSurname.setError("Please enter your last name.");
        else if (TextUtils.isEmpty(newEmail.getText().toString()))
            newEmail.setError("Please enter a valid e-mail address.");
        else if (!newEmail.getText().toString().contains("@"))
            newEmail.setError("Please enter a valid e-mail address.");
        else if (TextUtils.isEmpty(newPassword.getText().toString()))
            newPassword.setError("Please enter a password.");
        else if (TextUtils.isEmpty(repeatedPassword.getText().toString()))
            repeatedPassword.setError("Please re-enter your password.");
        else if (!newPassword.getText().toString().equals(repeatedPassword.getText().toString()))
            repeatedPassword.setError("The passwords must be the same.");
        else {
            User u = new User(newName.getText().toString(), newSurname.getText().toString(), newEmail.getText().toString(), newPassword.getText().toString());
            new ApiLogin(this, false).execute(u);
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
        }
    }
}
