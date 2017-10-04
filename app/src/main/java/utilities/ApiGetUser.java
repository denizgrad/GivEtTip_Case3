package utilities;

import android.os.AsyncTask;

import models.User;

public class ApiGetUser extends AsyncTask<String, Void, User> {
    @Override
    protected User doInBackground(String... params) {
        User ret = null;
        String userId = params[0];
        try {
            ret = ApiUtility.getHttpGetResponse("users", String.valueOf(userId), User.class);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    protected void onPostExecute(User result){
        super.onPostExecute(result);
    }
}
