package com.example.startedservice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ResultReceiver;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class handler extends Handler {

    private static final String TAG = "mytag";
    private MYDownloadedService myDownloadedService;
    private static final String myservice_message = "myservice_message";
    private Context mContext;

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);

        downloadSong(msg.obj.toString());

        myDownloadedService.stopSelf(msg.arg1);
        // stopselfResult should be used we'll do it some other time............

        Intent intent = new Intent(myservice_message);
        intent.putExtra(MainActivity.MY_INTENT_KEY,msg.obj.toString());

        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);




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

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }


}
