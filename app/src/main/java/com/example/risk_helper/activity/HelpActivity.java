package com.example.risk_helper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.risk_helper.R;

public class HelpActivity extends AppCompatActivity {

    ImageButton goBackToMainActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);


        goBackToMainActivityButton = findViewById(R.id.return_from_help_to_main_button);

        goBackToMainActivityButton.setOnClickListener(v -> finish());


    }
}