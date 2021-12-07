package com.example.risk_helper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.risk_helper.R;

import java.io.BufferedReader;

public class MainActivity extends AppCompatActivity {

    Button welcomeContinueButton;
    Button welcomeHelpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcomeContinueButton = findViewById(R.id.welcome_continue_button);
        welcomeHelpButton = findViewById(R.id.welcome_help_button);

        welcomeContinueButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, InputFieldActivity.class);
            startActivity(intent);
        });

        welcomeHelpButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HelpActivity.class);
            startActivity(intent);
        });
    }
}