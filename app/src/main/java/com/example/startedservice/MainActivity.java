package com.example.startedservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    ResultReceiver mResultReceiver;

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

    private void btnStartClicked() {

    for(String song : Playlist.songs)
    {
        Handler handler = new Handler();
        mResultReceiver = new resultReciever(handler);

        // on recieve result needs to be completed

        Intent intent = new Intent(MainActivity.this,MYDownloadedService.class);
        intent.putExtra(MY_INTENT_KEY,song);
        intent.putExtra(Intent.EXTRA_RESULT_RECEIVER,mResultReceiver);
        startService(intent);
    }
    }

    public void setTextView(String result)
    {
        tvResult.append(result +" downloaded" + "\n");
    }

    public class resultReciever extends ResultReceiver {
        /**
         * Create a new ResultReceive to receive results.  Your
         * {@link #onReceiveResult} method will be called from the thread running
         * <var>handler</var> if given, or from an arbitrary thread if null.
         *
         * @param handler
         */
        public resultReciever(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            if(resultCode == RESULT_OK) setTextView(resultData.getString(MY_INTENT_KEY));

        }
    }
}
