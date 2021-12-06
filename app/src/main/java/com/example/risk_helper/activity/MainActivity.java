package com.example.risk_helper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.risk_helper.R;

public class MainActivity extends AppCompatActivity {

    TextView welcomeContinueTextViewAsButton;
    TextView welcomeHelpTextViewAsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcomeContinueTextViewAsButton = findViewById(R.id.welcome_continue_textView);
        welcomeHelpTextViewAsButton = findViewById(R.id.welcome_help_textView);

        welcomeContinueTextViewAsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InputFieldActivity.class);
                startActivity(intent);
            }
        });

        welcomeHelpTextViewAsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HelpActivity.class);
            startActivity(intent);
        });
    }
}