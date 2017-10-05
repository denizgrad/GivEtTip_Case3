package com.example.nermi.gitettip;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ReportTipActivity extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_tip);

        Intent intent = getIntent();
        Uri imageUri = Uri.parse(intent.getStringExtra("IMAGE"));

       // Bitmap bm = BitmapFactory.decodeByteArray(byteArrayExtra, 0, byteArrayExtra.length);

        iv = (ImageView) findViewById(R.id.tipCapture);
        iv.setImageURI(imageUri);
        iv.setRotation(90);
    }
}
