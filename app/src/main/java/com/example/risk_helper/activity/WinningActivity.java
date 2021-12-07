package com.example.risk_helper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.risk_helper.R;
import com.example.risk_helper.other.IntentPackageWin;

public class WinningActivity extends AppCompatActivity {

    TextView winnerTextView;

    ImageButton fromWinToMainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winning);
        Intent intent = getIntent();
        IntentPackageWin intentPackageWin = (IntentPackageWin) intent.getSerializableExtra("winner");
        String winner = intentPackageWin.getWinner();

        fromWinToMainButton = findViewById(R.id.return_from_win_to_main_button);

        winnerTextView = findViewById(R.id.winner_textView);
        String winningText = "Congratulations! " + winner + " has won the current war";
        winnerTextView.setText(winningText);


        fromWinToMainButton.setOnClickListener(v -> {
            Intent intent1 = new Intent(WinningActivity.this, MainActivity.class);
            finish();
            overridePendingTransition(0,0);
            startActivity(intent1);
            overridePendingTransition(0,0);
        });





    }
}