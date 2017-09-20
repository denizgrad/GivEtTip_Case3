package utilities;

import android.util.Log;

import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import models.User;

/**
 * Created by Matic-ProBook on 20. 09. 2017.
 */

public class ApiLogin {
    public static int RESPONSE_CODE = 0;
    public static User USER = null;

    public static boolean checkCredentials(User u) {
        USER = u;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(AppConstants.API_URL + "users/");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    conn.setRequestProperty("Authorization", "Basic " + USER.getEmail() + ":" + USER.getPassword());
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    Gson gson = new Gson();
                    String json = gson.toJson(USER);

                    Log.i("JSON", json);
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    os.writeBytes(json);
                    os.flush();
                    os.close();

                    RESPONSE_CODE = conn.getResponseCode();
                    String responseMessage = conn.getResponseMessage();
                    Log.i("STATUS", String.valueOf(RESPONSE_CODE));
                    Log.i("MSG", responseMessage);
                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("error", e.toString());
                }
            }


        });

        thread.start();
        // TODO !!!
        if(!thread.isAlive()) {
            int responseCode = RESPONSE_CODE;
            RESPONSE_CODE = 0;
            USER = null;

            if (responseCode == 200)
                return true;
            else
                return false;
        }
    }
}
