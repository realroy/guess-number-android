package com.realroy.guessthenumbergame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button startGame_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initComponent();
    }

    public void initComponent() {
        startGame_btn = (Button) findViewById(R.id.startGame_btn);
        startGame_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intend = new Intent(MenuActivity.this, GameActivity.class);
                startActivity(intend);
                finish();
            }
        });
    }
}
