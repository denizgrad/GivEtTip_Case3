package utilities;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import models.Record;
import models.Response;

public class ApiPostRecord extends AsyncTask<Record, Void, Response> {
    private Uri imageUri;
    private Activity activity;

    public ApiPostRecord(Uri imageUri, Activity activity) {
        this.imageUri = imageUri;
        this.activity = activity;
    }

    // This is run in a background thread
    @Override
    protected Response doInBackground(Record... params) {
        try {
            Record r = params[0];
            String encodedImage = ImageUtility.encodeImageCompressed(imageUri.getPath());
            r.setImagePath(encodedImage);

            Gson gson = new Gson();
            String json = gson.toJson(r);

            MultipartUtility muti = new MultipartUtility("records", "UTF-8");
            muti.addFilePart("jsonFile" , json);

            String stream = muti.finish();

            Response target = gson.fromJson(stream, Response.class); // deserializes json into target
            return target;
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

//    public File writeToFile(StringBuffer sb)
//            throws IOException {
////        String filePath =  Environment.getExternalStorageDirectory().getAbsolutePath()+"/tmpJson.txt";
//      String filePath =   activity.getApplicationContext().getFilesDir().getPath().toString() + "/tmpJson.txt";
//        File f = new File(filePath);
//        if(!f.exists()){
//            f.mkdirs();
//        }
//        FileWriter fileWriter = new FileWriter(f.getName(), true);
//        BufferedWriter bw = new BufferedWriter(fileWriter);
//        bw.write(sb.toString());
//        bw.close();
//        return f;
//    }
}
