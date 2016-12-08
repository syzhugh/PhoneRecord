package com.test.sun.phonerecord;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.test.sun.phonerecord.util.UploadUtil;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intentX = new Intent();
        intentX.setClassName(this, "com.test.sun.phonerecord.TestPhone.MonitorService");
        startService(intentX);

        UploadUtil uploadUtil = new UploadUtil();
        uploadUtil.addCallBack(new UploadUtil.UploadCallBack() {
            @Override
            public void failedCallBack() {
            }

            @Override
            public void successCallBack(String string) {

            }
        });
        File directory = Environment.getExternalStorageDirectory();
        File file = new File(directory, "logo.jpg");
        Log.i("info", ":" + file.exists());
        uploadUtil.uploadFile("http://192.168.1.103:8080/UploadServer/myServer", file.getAbsolutePath());

    }
}
