package com.example.startedservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String MY_INTENT_KEY = "mykey";
    Button btnStart;
    TextView tvResult;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            setTextView(intent.getStringExtra(MY_INTENT_KEY));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnStart = findViewById(R.id.btnStart);
        tvResult = findViewById(R.id.tvResult);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStartClicked();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,new IntentFilter());
    }

    private void btnStartClicked() {

    for(String song : Playlist.songs)
    {

        Intent intent = new Intent(MainActivity.this,MYDownloadedService.class);
        intent.putExtra(MY_INTENT_KEY,song);
        startService(intent);
    }
    }

    public void setTextView(String result)
    {
        tvResult.append(result +" downloaded" + "\n");
    }


}
