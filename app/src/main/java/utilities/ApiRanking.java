package utilities;

import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import java.util.List;
import models.Rank;

public class ApiRanking extends AsyncTask<Void, Void, List<Rank>> {

    LayoutInflater inflater;

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
    protected void onPostExecute(List<Rank> ranks) {
        super.onPostExecute(ranks);

        String[] ranksStrings = new String[ranks.size()];
        int i = 0;
        for (Rank r : ranks) {
            ranksStrings[0] = r.getScoreString();
            i++;
        }
        AppConstants.RANK_STRINGS = ranksStrings;
    }
}
