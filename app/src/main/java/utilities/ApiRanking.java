package utilities;

import android.os.AsyncTask;
import android.util.Log;
import java.util.List;
import models.Rank;

public class ApiRanking extends AsyncTask<Void, Void, List<Rank>> {

    // This is run in a background thread
    @Override
    protected List<Rank> doInBackground(Void... params) {
        try {
            List<Rank> response = ApiUtility.getRank("rankings");
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("error", e.toString());
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Rank> coords) {
        super.onPostExecute(coords);
        //TODO: Do something with rankings. Show them in the gragment.


    }
}
