package com.realroy.guessthenumbergame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    private final int   TIME_LIMIT = 60;

    private int         timeRunner,
                        answer,
                        score;

    private TextView    showTime_textView,
                        showHint_textView,
                        showScore_textView;
    private EditText    editText;

    private Button      confirm_btn;

    private Timer       timer;

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
                        Intent intent = new Intent(GameActivity.this, SumScoreActivity.class);
                        intent.putExtra("score", score);
                        startActivity(intent);
                        finish();
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

    @Override
    protected void onDestroy() {
        AlertDialog.Builder confirmExit_dialog = new AlertDialog.Builder(GameActivity.this);
        confirmExit_dialog.setMessage("Do you want to exit?");
        confirmExit_dialog.setCancelable(true);
        confirmExit_dialog.setPositiveButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface d, int which) {

            }
        });
        confirmExit_dialog.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface d, int which) {
                finish();
            }
        });
        super.onDestroy();
    }

    public void randomNumber(){
        answer = (int) Math.round(Math.random() * 100);
    }

    public void initComponent() {
        randomNumber();

        showHint_textView = (TextView) findViewById(R.id.showHint_textView);
        showHint_textView.setText(R.string.guide);

        timeRunner = 0;
        showTime_textView = (TextView) findViewById(R.id.showTime_textView);
        showTime_textView.setText(String.valueOf(TIME_LIMIT - timeRunner));

        showScore_textView = (TextView) findViewById(R.id.showScore_textView);
        showScore_textView.setText(String.valueOf(score));

        editText = (EditText) findViewById(R.id.editText);

        confirm_btn = (Button) findViewById(R.id.confirm_btn);
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String input = editText.getText().toString();
                    int guess = Integer.parseInt(input);
                    checkAnswer(guess);
                } catch (NullPointerException error) {
                    showHint_textView.setText(R.string.enter_number);
                } catch (ClassCastException error) {
                    showHint_textView.setText(R.string.enter_number);
                }
            }
        });
    }

    /*public void initValue() {
        randomNumber();
        showHint_textView.setText(R.string.guide);
        timeRunner = 0;

    }*/

    public void checkAnswer(int guess) {
        if(answer == guess) {
            showHint_textView.setText(R.string.correct);
            randomNumber();
            score++;
            timeRunner -= 5;
            showScore_textView.setText(String.valueOf(score));

        }
        else if(answer > guess) {

            showHint_textView.setText(R.string.too_low);
        }
        else if(answer < guess) {
            showHint_textView.setText(R.string.too_high);
        }
    }

    public void startTimer() {
        timer = new Timer();
        timer.schedule(new MyTimerTask(), 0 , 1000);
    }

}
