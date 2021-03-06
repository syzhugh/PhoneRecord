package com.test.sun.phonerecord.util;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
        Log.i("info", ":exists");


        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);

        RequestBody requestBody = null;
        try {
            requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart(URLEncoder.encode("{\"tt\" = \"孙耀宗\",\"customer\"=\"孙策\",\"time\"=123124123123123}", "utf-8"), file.getName(), fileBody)
                    .build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = builder
                //设置超时
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        Call call1 = okHttpClient.newCall(request);
        call1.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("info", "UploadUtil:onFailure----------------------");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("info", "UploadUtil:onResponse----------------------");
            }
        });


//        final Request request = new Request.Builder()
//                .url(url)
//                .post(filebody)
//                .build();
//
//        mOkHttpClient.newBuilder().writeTimeout(5000, TimeUnit.SECONDS);
//        Call call = mOkHttpClient.newCall(request);
//
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.i("info", "UploadUtil:onFailure----------------------");
//                callBack.failedCallBack();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) {
//                if (response.isSuccessful()) {
//                    Log.i("info", "UploadUtil:onResponse----------------------");
//                    try {
//                        String string = response.body().string();
//                        callBack.successCallBack(string);
//                    } catch (IOException e) {
//                        callBack.failedCallBack();
//                        e.printStackTrace();
//                    }
//                } else {
//                    callBack.failedCallBack();
//                }
//            }
//        });

    }


}
