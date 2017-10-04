package utilities;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.nermi.gitettip.UserActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.List;
import models.RecordCoordinate;

public class ApiRecordCoordinate extends AsyncTask<Void, Void, List<RecordCoordinate>> {

    GoogleMap googleMap;

    public ApiRecordCoordinate(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    // This is run in a background thread
    @Override
    protected List<RecordCoordinate> doInBackground(Void... params) {
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
        for(RecordCoordinate rc : coords){
            googleMap.addMarker(new MarkerOptions().position(new LatLng(rc.getGpsLatitude(), rc.getGpsLongitude()))
                    .title(rc.getAuthorEmail()));
        }
    }
}
