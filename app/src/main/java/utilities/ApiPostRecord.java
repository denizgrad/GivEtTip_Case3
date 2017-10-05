package utilities;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import com.google.gson.Gson;
import models.Record;
import models.Response;

public class ApiPostRecord extends AsyncTask<Record, Void, Response> {
    private Uri imageUri;

    public ApiPostRecord(Uri imageUri) {
        this.imageUri = imageUri;
    }

    // This is run in a background thread
    @Override
    protected Response doInBackground(Record... params) {
        try {
            Record r = params[0];
            String encodedImage = ImageUtility.encodeImageEfficient(imageUri.getPath());
            r.setImagePath(encodedImage);
            Gson gson = new Gson();
            String json = gson.toJson(r);
            Log.i("JSON", json);
            Response response = ApiUtility.getHttpPostResponse("records", json, Response.class);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("error", e.toString());
            return null;
        }
    }

    @Override
    protected void onPostExecute(Response response) {
        super.onPostExecute(response);
    }
}
