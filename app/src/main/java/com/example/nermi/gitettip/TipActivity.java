package com.example.nermi.gitettip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class TipActivity extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);
        tv = (TextView) findViewById(R.id.tipDescription);
        tv.setMovementMethod(new ScrollingMovementMethod());
    }
}
