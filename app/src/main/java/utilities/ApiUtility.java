package utilities;

import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.AuthPolicy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import models.Response;

/**
 * Created by Matic-ProBook on 21. 09. 2017.
 */

public class ApiUtility {
    private static HttpURLConnection prepareConnection(String urlEnding, String httpMethod, String json) {
        try {
            URL url = new URL(AppConstants.API_URL + urlEnding);

            byte[] data = AppConstants.API_CREDENTIALS.getBytes("UTF-8");
            String base64 = Base64.encodeToString(data, Base64.DEFAULT);
            String encodedCredentials = "Basic " + base64;

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(httpMethod);
            if (!httpMethod.equals("GET"))
                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Authorization", encodedCredentials);
            conn.setRequestProperty("Accept", "application/json");
            if (!httpMethod.equals("GET"))
                conn.setDoOutput(true);
            conn.setDoInput(true);

            if (!httpMethod.equals("GET")) {
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                os.writeBytes(json);
                os.flush();
                os.close();
            }

//            int responseCode = conn.getResponseCode();
//            String responseMessage = conn.getResponseMessage();
//            Log.i("STATUS", String.valueOf(responseCode));
//            Log.i("MSG", responseMessage);

            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("error", e.toString());
            return null;
        }
    }
    public static byte[] getEncodedCredentials() throws UnsupportedEncodingException {
        byte[] data = AppConstants.API_CREDENTIALS.getBytes("UTF-8");
        String base64 = Base64.encodeToString(data, Base64.DEFAULT);
        String encodedCredentials = "Basic " + base64;
        return data;
    }


    private static String getInputStream(HttpURLConnection conn) {
        try {
            StringBuilder result = new StringBuilder();
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            return String.valueOf(result);
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("error", e.toString());
            return null;
        }
    }

    public static Response getResponse(String urlEnding, String httpMethod, String json) {
        try {
            HttpURLConnection conn = prepareConnection(urlEnding, httpMethod, json);
            String stream = getInputStream(conn);
            Gson gson = new Gson();
            Response response = gson.fromJson(String.valueOf(stream), Response.class);
            conn.disconnect();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("error", e.toString());
            return null;
        }
    }

    public static <T> T getHttpGetResponse(String urlEnding, String parameter, Class<T> type) throws Exception{
        HttpURLConnection conn = prepareConnection(urlEnding + "/" + parameter, "GET", null);
        String stream = getInputStream(conn);

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ").create();
        T target = gson.fromJson(stream, type); // deserializes json into target
//        conn.disconnect();
        return target;
    }
}
