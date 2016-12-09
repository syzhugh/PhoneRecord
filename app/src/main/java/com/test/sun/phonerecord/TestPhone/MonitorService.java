package com.test.sun.phonerecord.TestPhone;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MonitorService extends Service {

    public static final String TAG = "TestVar";
    //    public static final String TAG = "MonitorService";
    private TelephonyManager manager;
    private TelListener listener;

    @Override

    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "MonitorService:onCreate----------------");
        manager = (TelephonyManager) getApplicationContext().getSystemService(TELEPHONY_SERVICE);
        listener = new TelListener();
        manager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
