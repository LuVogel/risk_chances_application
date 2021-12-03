package com.example.risk_helper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.risk_helper.R;

public class MainActivity extends AppCompatActivity {

    TextView welcomeScreenTextView;
    ImageButton welcomeNextScreenButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcomeScreenTextView = findViewById(R.id.welcome_textView);
        welcomeNextScreenButton = findViewById(R.id.welcome_next_WindowButton);

        welcomeNextScreenButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InputFieldActivity.class);
                startActivity(intent);
            }
        });
    }
}