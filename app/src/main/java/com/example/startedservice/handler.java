package com.example.startedservice;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ResultReceiver;
import android.util.Log;

public class handler extends Handler {

    private static final String TAG = "mytag";
    private MYDownloadedService myDownloadedService;
    private ResultReceiver mresultReceiver;

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);

        downloadSong(msg.obj.toString());

        myDownloadedService.stopSelf(msg.arg1);
        // stopselfResult should be used we'll do it some other time............


        Bundle bundle = new Bundle();
        bundle.putString(MainActivity.MY_INTENT_KEY,msg.obj.toString());
        mresultReceiver.send(MainActivity.RESULT_OK,bundle);




    }

    private void downloadSong(String song) {
        Log.d(TAG, "downloadSong: " + song + "started");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "downloadSong: " + song + "downloaded");
    }

    public void setMyDownloadedService(MYDownloadedService myDownloadedService) {
        this.myDownloadedService = myDownloadedService;
    }

    public void setMresultReceiver(ResultReceiver mresultReceiver) {
        this.mresultReceiver = mresultReceiver;
    }
}
