package com.example.nermi.gitettip;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import models.Record;
import models.User;
import utilities.ApiPostRecord;
import utilities.SharedPreferencesUtility;

public class ReportTipActivity extends AppCompatActivity {

    ImageView iv;
    TextView descriptionView;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_tip);

        Intent intent = getIntent();
        imageUri = Uri.parse(intent.getStringExtra("IMAGE"));
        // Bitmap bm = BitmapFactory.decodeByteArray(byteArrayExtra, 0, byteArrayExtra.length);
        iv = (ImageView) findViewById(R.id.tipCapture);
        iv.setImageURI(imageUri);
        iv.setRotation(90);
        double lattitude = intent.getDoubleExtra("LAT", 0);
        double longitude = intent.getDoubleExtra("LONG", 0);
        ///MATIC TODO

    }

    public void onSendTipClick(View view) {
        descriptionView = (TextView) findViewById(R.id.descriptionText);
        String description = descriptionView.getText().toString();

        User u = new User(SharedPreferencesUtility.readValue(this, "userId"));
        Record r = new Record(u, null, 1.01, 1.01, description);

        new ApiPostRecord(imageUri).execute(r);
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }
}
