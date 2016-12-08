package com.test.sun.phonerecord.TestPhone;

import android.media.MediaRecorder;
import android.os.Environment;
import android.telecom.CallAudioState;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * Created by ZS27 on 2016/12/8.
 */

public class TelListener extends PhoneStateListener {
    //    public static final String TAG = "TelListener";
    public static final String TAG = "TestVar";

    private MediaRecorder recorder;
    private File ddkj;

    public TelListener() {
        initDir();
    }

    private void initDir() {
        ddkj = new File(Environment.getExternalStorageDirectory(), "ddkj1");
        if (!ddkj.exists()) {
            ddkj.mkdir();
        }
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);

        Log.i(TAG, "TelListener:onCallStateChanged----------------");
        Log.i(TAG, ":" + incomingNumber);
        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                Log.i(TAG, ":CALL_STATE_IDLE");
                stopRecording();
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                Log.i(TAG, ":CALL_STATE_OFFHOOK");
                try {
                    startToRecord();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                Log.i(TAG, ":CALL_STATE_RINGING");

                break;
        }
    }

    private void stopRecording() {
        Log.i(TAG, "TelListener:stopRecording----------------");
        if (recorder != null) {
            recorder.stop();
            recorder.release();
            recorder = null;
        }
    }

    private void startToRecord() throws IOException {
        Log.i(TAG, "TelListener:startToRecord----------------");
        Log.i(TAG, ":" + ddkj.getAbsolutePath());
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);   //获得声音数据源
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);   // 按3gp格式输出
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(ddkj.getAbsolutePath() + "/" + System.currentTimeMillis() + ".3gp"); //输出文件
        recorder.prepare(); //准备
        recorder.start();

    }


}
