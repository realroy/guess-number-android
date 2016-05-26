package com.realroy.guessthenumbergame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SumScoreActivity extends AppCompatActivity {
    private final int   YOUR_SCORE = getIntent().getExtras().getInt("score"),
                        HIGH_SCORE = 0;

    private TextView    showYourScore_textView,
                        showHighScore_textView;

    private Button      toMainMenu_btn,
                        playAgain_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum_score);
        initComponent();
    }

    public void initComponent() {
        showYourScore_textView  = (TextView)    findViewById(R.id.showYourScore_textView);
        showHighScore_textView  = (TextView)    findViewById(R.id.showHighScore_textView);
        toMainMenu_btn          = (Button)      findViewById(R.id.toMainMenu_btn);
        playAgain_btn           = (Button)      findViewById(R.id.playAgain_btn);

        showYourScore_textView.setText(String.valueOf(YOUR_SCORE));
        showHighScore_textView.setText(String.valueOf(HIGH_SCORE));

        toMainMenu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SumScoreActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        playAgain_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SumScoreActivity.this, GameActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
