package utilities;

import android.os.AsyncTask;
import models.Record;

public class ApiGetRecord extends AsyncTask<String, Void, Record> {
    @Override
    protected Record doInBackground(String... params) {
        Record ret = null;
        String recordId = params[0];
        try {
            ret = ApiUtility.getHttpGetResponse("records", String.valueOf(recordId), Record.class);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    protected void onPostExecute(Record result){
        super.onPostExecute(result);
    }
}
