package utilities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.example.nermi.gitettip.MainActivity;
import com.example.nermi.gitettip.UserActivity;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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
            URL url = new URL(AppConstants.API_URL + "login");

            byte[] data = AppConstants.API_CREDENTIALS.getBytes("UTF-8");
            String base64 = Base64.encodeToString(data, Base64.DEFAULT);
            String encodedCredentials = "Basic " + base64;

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Authorization", encodedCredentials);
//            conn.setRequestProperty("Authorization", "Basic " + credentials);
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            Gson gson = new Gson();
            String json = gson.toJson(u);

            Log.i("JSON", json);
            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
            os.writeBytes(json);
            os.flush();
            os.close();

            int responseCode = conn.getResponseCode();
            String responseMessage = conn.getResponseMessage();
            Log.i("STATUS", String.valueOf(responseCode));
            Log.i("MSG", responseMessage);
            conn.disconnect();

            if (responseCode == 200) return true; else return false;

        } catch (Exception e) {
            e.printStackTrace();
            Log.i("error", e.toString());
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean authorized) {
        super.onPostExecute(authorized);
        if(authorized) {
            Toast.makeText(activity, "Successful login!", Toast.LENGTH_LONG).show();
            activity.startActivity(new Intent(activity, UserActivity.class));
        } else {
            Toast.makeText(activity, "The login credentials are not correct.", Toast.LENGTH_LONG).show();
        }
    }
}