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
 */

public class ApiLogin extends AsyncTask<User, Void, Boolean> {
    private Activity activity;

    public ApiLogin(Activity activity) {
        this.activity = activity;
    }

    // This is run in a background thread
    @Override
    protected Boolean doInBackground(User... params) {
        User u = params[0];
        try {
            Gson gson = new Gson();
            String json = gson.toJson(u);
            Log.i("JSON", json);

            Response response = ApiUtility.prepareConnection("login", "POST", json);
            if (response.getStatusCode() == 200) {
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
            Toast.makeText(activity, "Successful login! ID: " + Integer.toString(userId), Toast.LENGTH_LONG).show();
            activity.startActivity(new Intent(activity, UserActivity.class));
        } else {
            Toast.makeText(activity, "The login credentials are not correct.", Toast.LENGTH_LONG).show();
        }
    }
}
