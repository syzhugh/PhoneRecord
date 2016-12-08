package com.test.sun.phonerecord.util;

import android.media.MediaRecorder;

import java.io.File;
import java.io.IOException;

/**
 * Created by ZS27 on 2016/12/8.
 */

public class RecordUtil {

    public static RecordObject start() {
        return new RecordObject();
    }

    public static class RecordObject {
        private MRecorder mediaRecorder;
        private UploadUtil uploadUtil;
        private RecordCallback callback;

        private String stringPath;
        private String urlServer;

        public RecordObject() {
            mediaRecorder = new MRecorder();
            uploadUtil = new UploadUtil();
        }

        public RecordObject target() {

            return this;
        }

        public RecordObject addCallback(RecordCallback callback) {
            this.callback = callback;
            return this;
        }

        private File mediaFile;

        public void doRecord(String filePath) {
            try {
                mediaRecorder.startToRecord(null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void stopRecord() {
            mediaRecorder.stopRecording();
            uploadUtil.addCallBack(new UploadUtil.UploadCallBack() {
                @Override
                public void failedCallBack() {
                    callback.failRecord();
                }

                @Override
                public void successCallBack(String string) {
                    callback.sucessRecord();
                }
            });
            uploadUtil.uploadFile(stringPath, urlServer);
        }

    }

    public static interface RecordCallback {
        void sucessRecord();

        void failRecord();
    }

}
