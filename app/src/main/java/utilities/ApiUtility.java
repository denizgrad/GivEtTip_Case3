package utilities;

import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import models.Rank;
import models.RecordCoordinate;
import models.Response;

/**
 * Created by Matic-ProBook on 21. 09. 2017.
 */

public class ApiUtility {

    private static HttpURLConnection prepareConnection(String urlEnding, String httpMethod, String json) {
        try {
            boolean METHOD_GET = (httpMethod.equals("GET")) ? true : false;
            URL url = new URL(AppConstants.API_URL + urlEnding);

            byte[] data = AppConstants.API_CREDENTIALS.getBytes("UTF-8");
            String base64 = Base64.encodeToString(data, Base64.DEFAULT);
            String encodedCredentials = "Basic " + base64;

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(httpMethod);
            if (!METHOD_GET)
                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Authorization", encodedCredentials);
            conn.setRequestProperty("Accept", "application/json");
            if (!METHOD_GET)
                conn.setDoOutput(true);
            conn.setDoInput(true);

            if (!METHOD_GET) {
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

    public static List<RecordCoordinate> getRecordCoordinate(String urlEnding) {
        try {
            HttpURLConnection conn = prepareConnection(urlEnding, "GET", null);
            String stream = getInputStream(conn);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<RecordCoordinate>>(){}.getType();
            List<RecordCoordinate> response = gson.fromJson(String.valueOf(stream), listType);
            conn.disconnect();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("error", e.toString());
            return null;
        }
    }

    public static List<Rank> getRank(String urlEnding) {
        try {
            HttpURLConnection conn = prepareConnection(urlEnding, "GET", null);
            String stream = getInputStream(conn);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Rank>>(){}.getType();
            List<Rank> response = gson.fromJson(String.valueOf(stream), listType);
            conn.disconnect();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("error", e.toString());
            return null;
        }
    }
}
