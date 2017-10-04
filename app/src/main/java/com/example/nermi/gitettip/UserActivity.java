package com.example.nermi.gitettip;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TabHost.TabSpec;

import java.io.ByteArrayOutputStream;

public class UserActivity extends AppCompatActivity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        initializeTabs();

        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    private void initializeTabs(){
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

    //TODO: Return image in the right format (PNG or JPEG) and save under res folder
    //https://developer.android.com/training/camera/photobasics.html
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            //Return a bitmap of the captured image
            Bitmap bm = (Bitmap) data.getExtras().get("data");

            //Convert the bitmap to byte array
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            Log.e("IMAGE: ", byteArray.toString());
            Log.e("IMAGE LENGTH: ", Integer.toString(byteArray.length));
        }
    }

    public void goToCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //the startActivityForResult() method is protected by a condition that calls resolveActivity(),
        // which returns the first activity component that can handle the intent. Performing this check
        // is important because if you call startActivityForResult() using an intent that no app can handle,
        // your app will crash. So as long as the result is not null, it's safe to use the intent
        if(intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

    }

}
