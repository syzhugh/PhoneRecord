package com.test.sun.phonerecord.Upload;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by ZS27 on 2016/12/8.
 */

public class UploadUtil {
    public static final String TAG = "UploadUtil";
    private OkHttpClient mOkHttpClient;
    private UploadCallBack callBack;

    public UploadUtil(UploadCallBack callBack) {
        mOkHttpClient = new OkHttpClient.Builder().build();
        this.callBack = callBack;
    }

    public static interface UploadCallBack {
        void failedCallBack();

        void successCallBack();
    }

    public void uploadFile(String url, String filePath) {

        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }
        RequestBody filebody = RequestBody.create(MediaType.parse("application/octet-stream"), file);


        final Request request = new Request.Builder()
                .url("url")
                .post(filebody)
                .build();

        OkHttpClient build = mOkHttpClient.newBuilder()
                .writeTimeout(50, TimeUnit.SECONDS)
                .build();
        Call call = build.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.failedCallBack();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String string = response.body().string();
                    callBack.successCallBack();
                } else {
                    callBack.failedCallBack();
                }
            }
        });
    }


}
