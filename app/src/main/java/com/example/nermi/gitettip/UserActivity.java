package com.example.nermi.gitettip;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import utilities.ApiRanking;
import utilities.AppConstants;
import utilities.SharedPreferencesUtility;

public class UserActivity extends AppCompatActivity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    FloatingActionButton fab;
    String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        initializeTabs();

        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    private void initializeTabs() {
        // create the TabHost that will contain the Tabs
        FragmentTabHost tabHost = (FragmentTabHost) findViewById(R.id.tabHost);
        tabHost.setup(this.getBaseContext(), getSupportFragmentManager(), android.R.id.tabcontent);

        //New instanses of tabs
        TabSpec accountTab = tabHost.newTabSpec(getResources().getText(R.string.account).toString());
        TabSpec scoresTab = tabHost.newTabSpec(getResources().getText(R.string.scores).toString());
        TabSpec mapTab = tabHost.newTabSpec(getResources().getText(R.string.map).toString());

        // Set the Tab names
        accountTab.setIndicator(getResources().getText(R.string.account).toString());
        scoresTab.setIndicator(getResources().getText(R.string.scores).toString());
        mapTab.setIndicator(getResources().getText(R.string.map).toString());

        /* Add the tabs  to the TabHost to display, and choose the fragment to be
         * displayed when selecting a tab. */
        tabHost.addTab(accountTab, AccountFragment.class, null);
        tabHost.addTab(scoresTab, ScoresListFragment.class, null);
        tabHost.addTab(mapTab, MapFragment.class, null);
    }

    public void logOut(View view) {
        SharedPreferencesUtility.removeValue(this, "userId"); // log out the user
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);   // remove the stack with activities
        this.startActivity(intent); // go to main activity
    }

    //TODO: Return image in the right format (PNG or JPEG) and save under res folder
    //https://developer.android.com/training/camera/photobasics.html
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            //Return a bitmap of the captured image
            // Bitmap bm = (Bitmap) data.getExtras().get("data");

            //Convert the bitmap to byte array
            /*ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            Log.e("IMAGE: ", byteArray.toString());
            Log.e("IMAGE LENGTH: ", Integer.toString(byteArray.length));*/

            if (mCurrentPhotoPath == null)
                return;
            else {
                //Extract the location
                Location loc = getLocation();
                Intent intent = new Intent(this, ReportTipActivity.class);
                intent.putExtra("IMAGE", mCurrentPhotoPath);
                intent.putExtra("LAT", loc.getLatitude());
                intent.putExtra("LONG", loc.getLongitude());
                startActivity(intent);
            }
        }
    }

    MyLocationListener gps;

    private Location getLocation() {
        gps = new MyLocationListener(this);
        Location location = null;
        // check if GPS enabled
        if (gps.canGetLocation()) {
//            double latitude = gps.getLatitude();
//            double longitude = gps.getLongitude();
            location = gps.getLocation();
            // \n is for new line
//            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: "
//                    + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
        return location;
    }

    public void goToCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //the startActivityForResult() method is protected by a condition that calls resolveActivity(),
        // which returns the first activity component that can handle the intent. Performing this check
        // is important because if you call startActivityForResult() using an intent that no app can handle,
        // your app will crash. So as long as the result is not null, it's safe to use the intent
        if(intent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }

            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        }

    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "PNG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".png",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

}
