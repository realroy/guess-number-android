package com.realroy.guessthenumbergame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    private final int TIME_LIMIT = 60;
    private int timeRunner;
    private TextView showTime_textView;
    private Timer timer;

    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            GameActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if((TIME_LIMIT - timeRunner) > 0) {
                        timeRunner++;
                        showTime_textView.setText(String.valueOf(TIME_LIMIT - timeRunner));
                    } else{
                        timer.cancel();
                    }
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initComponent();
        startTimer();
    }

    public void initComponent() {
        timeRunner = 0;
        showTime_textView = (TextView) findViewById(R.id.showTime_textView);
        showTime_textView.setText(String.valueOf(TIME_LIMIT - timeRunner));
    }

    public void startTimer() {
        timer = new Timer();
        timer.schedule(new MyTimerTask(), 0 , 1000);
    }
}
