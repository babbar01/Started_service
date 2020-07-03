package com.example.startedservice;

import android.os.Looper;
import android.util.Log;

import java.util.logging.Handler;

public class DownloadThread extends Thread {

    private static final String TAG = "mytag";
    handler mHandler;


    @Override
    public void run() {

        Looper.prepare();
        mHandler = new handler();
        Looper.loop();

    }


}
