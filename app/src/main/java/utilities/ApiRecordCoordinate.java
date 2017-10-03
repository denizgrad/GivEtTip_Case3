package utilities;

import android.os.AsyncTask;
import android.util.Log;
import java.util.List;
import models.RecordCoordinate;

public class ApiRecordCoordinate extends AsyncTask<Void, Void, List<RecordCoordinate>> {

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
        //TODO: Do something with this list of points (coords) - show them on the map.
    }
}
