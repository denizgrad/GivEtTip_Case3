package com.example.nermi.gitettip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import models.Record;
import utilities.ApiGetRecord;

public class TipActivity extends AppCompatActivity {

    ImageView iv;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);
        tv = (TextView) findViewById(R.id.tipDescription);
        tv.setMovementMethod(new ScrollingMovementMethod());
        iv = (ImageView) findViewById(R.id.tipImage);

        ApiGetRecord getRecord = new ApiGetRecord();
        try {
            int recordId = getIntent().getIntExtra("RECORD_ID", -1);
            Record r = getRecord.execute(String.valueOf(recordId)).get();
            tv.setText(r.getDescription());
            iv.setImageBitmap(r.getImage());
            iv.setRotation(90);
        } catch (Exception e) {
            Log.e("ERROR: ", e.getLocalizedMessage());
        }
    }
}
