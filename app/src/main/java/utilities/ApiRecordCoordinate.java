package utilities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.nermi.gitettip.UserActivity;
import com.google.gson.Gson;

import java.util.List;

import models.RecordCoordinate;
import models.Response;
import models.User;

public class ApiRecordCoordinate extends AsyncTask<Boolean, Void, List<RecordCoordinate>> {

    // This is run in a background thread
    @Override
    protected List<RecordCoordinate> doInBackground(Boolean... params) {
        try {
            List<RecordCoordinate> response = ApiUtility.getRecordCoordinate("recordscoordinates");
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("error", e.toString());
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<RecordCoordinate> coords) {
        super.onPostExecute(coords);
        //TODO: Do something with this list of points - show them on the map.
        int n = 5;
    }
}
