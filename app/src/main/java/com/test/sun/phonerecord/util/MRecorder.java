package com.test.sun.phonerecord.util;

import android.media.MediaRecorder;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * Created by ZS27 on 2016/12/8.
 */

public class MRecorder {

    private MediaRecorder recorder;

    public MRecorder() {
        this.recorder = new MediaRecorder();
    }

    public void stopRecording() {
        if (recorder != null) {
            recorder.stop();
            recorder.release();
            recorder = null;
        }
    }

    public void startToRecord(File file) throws IOException {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);   //获得声音数据源
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS);   // 按3gp格式输出
        recorder.setOutputFile(file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".aac"); //输出文件
        recorder.prepare(); //准备
        recorder.start();

    }
}
