package com.test.sun.phonerecord.TestPhone;

import android.media.MediaRecorder;
import android.os.Environment;
import android.telecom.CallAudioState;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.czt.mp3recorder.MP3Recorder;

import java.io.File;
import java.io.IOException;

/**
 * Created by ZS27 on 2016/12/8.
 */

public class TelListener extends PhoneStateListener {
    //    public static final String TAG = "TelListener";
    public static final String TAG = "TestVar";

    private MediaRecorder recorderAAC;
    private MediaRecorder recorderAMR;

    private MP3Recorder mRecorder;

    private File ddkj;

    public TelListener() {
        initDir();

    }

    private void initDir() {
        ddkj = new File(Environment.getExternalStorageDirectory(), "ddkj1");
        if (!ddkj.exists()) {
            ddkj.mkdir();
        }
//        if (ddkj.exists()) {
//            mRecorder = new MP3Recorder(new File(ddkj, "test.mp3"));
//        }
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);

        Log.i(TAG, "TelListener:onCallStateChanged----------------");
        Log.i(TAG, ":" + incomingNumber);
        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                Log.i(TAG, ":CALL_STATE_IDLE");
                stopRecording(recorderAAC);
//                stopRecording(recorderAMR);
//                mRecorder.stop();
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                Log.i(TAG, ":CALL_STATE_OFFHOOK");
                try {
                    startToRecord(recorderAAC, MediaRecorder.OutputFormat.AAC_ADTS, MediaRecorder.AudioEncoder.AAC, ".aac");
//                    startToRecord(recorderAMR, MediaRecorder.OutputFormat.AMR_WB, MediaRecorder.AudioEncoder.AMR_WB, ".amr");
//                    mRecorder.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                Log.i(TAG, ":CALL_STATE_RINGING");
                break;
        }
    }

    private void stopRecording(MediaRecorder recorder) {
        Log.i(TAG, "TelListener:stopRecording----------------");
        if (recorder != null) {
            recorder.stop();
            recorder.release();
            recorder = null;
        }
    }

    private void startToRecord(MediaRecorder recorder, int format, int ecode, String tail) throws IOException {
        Log.i(TAG, "TelListener:startToRecord----------------");
        Log.i(TAG, ":" + ddkj.getAbsolutePath());
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);   //获得声音数据源
        recorder.setOutputFormat(format);   // 按3gp格式输出
        recorder.setAudioEncoder(ecode);
        recorder.setOutputFile(ddkj.getAbsolutePath() + "/" + System.currentTimeMillis() + tail); //输出文件
        recorder.prepare(); //准备
        recorder.start();
    }

//    private void startToRecord(MediaRecorder recorder, int format, int ecode, String tail) throws IOException {
//        Log.i(TAG, "TelListener:startToRecord----------------");
//        Log.i(TAG, ":" + ddkj.getAbsolutePath());
//        recorder = new MediaRecorder();
//        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);   //获得声音数据源
//        recorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_WB);   // 按3gp格式输出
//        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);
////        recorder.setOutputFile(ddkj.getAbsolutePath() + "/" + System.currentTimeMillis() + ".3gp"); //输出文件
////        recorder.setOutputFile(ddkj.getAbsolutePath() + "/" + System.currentTimeMillis() + ".aac"); //输出文件
//        recorder.setOutputFile(ddkj.getAbsolutePath() + "/" + System.currentTimeMillis() + ".amr"); //输出文件
//        recorder.prepare(); //准备
//        recorder.start();
//    }


}
