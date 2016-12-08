package com.test.sun.phonerecord.util;

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

    public UploadUtil() {
        mOkHttpClient = new OkHttpClient.Builder().build();
    }

    public UploadUtil addCallBack(UploadCallBack callBack) {
        this.callBack = callBack;
        return this;
    }

    public static interface UploadCallBack {
        void failedCallBack();

        void successCallBack(String string);
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

        mOkHttpClient.newBuilder().writeTimeout(50, TimeUnit.SECONDS);
        Call call = mOkHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.failedCallBack();
            }

            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    try {
                        String string = response.body().string();
                        callBack.successCallBack(string);
                    } catch (IOException e) {
                        callBack.failedCallBack();
                        e.printStackTrace();
                    }
                } else {
                    callBack.failedCallBack();
                }
            }
        });

    }


}
