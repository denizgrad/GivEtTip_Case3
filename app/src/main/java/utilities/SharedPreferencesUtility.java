package utilities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Matic-ProBook on 21. 09. 2017.
 */

public class SharedPreferencesUtility {

    public static final String PREF_FILE_NAME = "GetEtTipPreff";

    public static void saveValueOld(Activity activity, String key, int value) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void saveValue(Context c, String key, int value) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int readValueOld(Activity activity, String key) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getInt(key, -1);
    }

    public static int readValue(Context c, String key) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(c);
        return sharedPref.getInt(key, -1);
    }

    public static void removeValueOld(Activity activity, String key) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(key);
        editor.apply();
    }

    public static void removeValue(Context c, String key) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(key);
        editor.apply();
    }
}
