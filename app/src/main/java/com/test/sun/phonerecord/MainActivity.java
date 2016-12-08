package com.test.sun.phonerecord;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intentX = new Intent();
        intentX.setClassName(this, "com.test.sun.phonerecord.TestPhone.MonitorService");
        startService(intentX);
    }
}
