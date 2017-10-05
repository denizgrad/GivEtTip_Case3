package utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.nermi.gitettip.UserActivity;
import com.google.gson.Gson;

import models.Response;
import models.User;

/**
 * Created by Matic-ProBook on 20. 09. 2017.
 * It is a AsyncTask for login to application or creating a new account.
 */

public class ApiLogin extends AsyncTask<User, Void, Boolean> {
    private Activity activity;
    private boolean login;  // to distinguish between login and signup
    private int responseCode;

    public ApiLogin(Activity activity, boolean login) {
        this.activity = activity;
        this.login = login;
    }

    // This is run in a background thread
    @Override
    protected Boolean doInBackground(User... params) {
        User u = params[0];
        try {
            Gson gson = new Gson();
            String json = gson.toJson(u);
            Log.i("JSON", json);

            String urlEnding = login ? "login" : "users";
            Response response = ApiUtility.getHttpPostResponse(urlEnding, json, Response.class);
            this.responseCode = response.getStatusCode();
            if (this.responseCode == 200 || this.responseCode == 201) {
                int userId = Integer.parseInt(response.getReturnKey());
                SharedPreferencesUtility.saveValue(activity, "userId", userId);
                return true;
            } else
                return false;

        } catch (Exception e) {
            e.printStackTrace();
            Log.i("error", e.toString());
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean authorized) {
        super.onPostExecute(authorized);
        if (authorized) {
            int userId = SharedPreferencesUtility.readValue(activity, "userId");
            if (login)
                Toast.makeText(activity, "Successful login! ID: " + Integer.toString(userId), Toast.LENGTH_LONG).show();
            else
                Toast.makeText(activity, "Your account was successfully created! ID: " + Integer.toString(userId), Toast.LENGTH_LONG).show();
          
            Intent intent = new Intent(activity, UserActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            activity.startActivity(intent);
        } else {
            if (login) {
                if (this.responseCode == 500)
                    Toast.makeText(activity, "Sorry, we have a problem with login server.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(activity, "The login credentials are not correct.", Toast.LENGTH_LONG).show();
            } else {
                if (this.responseCode == 409)
                    Toast.makeText(activity, "User with this email already exists.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(activity, "Sorry! An error occurred when creating your account.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
