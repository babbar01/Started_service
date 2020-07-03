package com.example.startedservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.os.ResultReceiver;

public class MYDownloadedService extends Service {

    DownloadThread mdownloadThread;

    public MYDownloadedService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mdownloadThread = new DownloadThread();
        mdownloadThread.start();

        while(mdownloadThread.mHandler == null);

        mdownloadThread.mHandler.setMyDownloadedService(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Message msg = Message.obtain();
        msg.obj = intent.getStringExtra(MainActivity.MY_INTENT_KEY);
        msg.arg1 = startId;
        mdownloadThread.mHandler.sendMessage(msg);

        mdownloadThread.mHandler.setMresultReceiver((ResultReceiver)
                intent.getParcelableExtra(Intent.EXTRA_RESULT_RECEIVER));
        // casted to result reciever because it is returning parcelable check by Ctrl+Shift+P

        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }
}
