package com.example.risk_helper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.risk_helper.R;
import com.example.risk_helper.other.InformationForWar;
import com.example.risk_helper.other.IntentPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SpecificFieldActivity extends AppCompatActivity {

    ImageButton returnToResultOfCalculationActivity, subAttackOne, subDefendOne, subBothOne,
            subAttackTwo, subDefendTwo;
    TextView attackerSpecificField, defenderSpecificField, attackerWinSpecificField,
            defenderWinSpecificField, remainingAttackerSpecificField, remainingDefenderSpecificField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_field);
        Intent intent = getIntent();
        IntentPackage intentPackage =
                (IntentPackage) intent.getSerializableExtra("currentInformation");
        InformationForWar informationForWar = intentPackage.getInformationForWar();

        attackerSpecificField = findViewById(R.id.attacker_specific_field);
        defenderSpecificField = findViewById(R.id.defender_specific_field);
        returnToResultOfCalculationActivity = findViewById(R.id.returnToResultOfCalculation);
        attackerWinSpecificField = findViewById(R.id.attacker_win_specific_field);
        defenderWinSpecificField = findViewById(R.id.defender_win_specific_field);
        remainingAttackerSpecificField = findViewById(R.id.remaining_attacker_specific_field);
        remainingDefenderSpecificField = findViewById(R.id.remaining_defender_specific_field);
        subAttackOne = findViewById(R.id.subAttackOne);
        subAttackTwo = findViewById(R.id.subAttackTwo);
        subDefendOne = findViewById(R.id.subDefendOne);
        subDefendTwo = findViewById(R.id.subDefendTwo);
        subBothOne = findViewById(R.id.subEachOne);

        attackerSpecificField.setText(informationForWar.getAttack_name());
        defenderSpecificField.setText(informationForWar.getDefend_name());
        remainingAttackerSpecificField.setText(informationForWar.getRemaining_att_name());
        remainingDefenderSpecificField.setText(informationForWar.getRemaining_def_name());
        attackerWinSpecificField.setText(informationForWar.getAtt_win_name());
        defenderWinSpecificField.setText(informationForWar.getDef_win_name());

        subAttackOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InformationForWar information =
                        readFileWithProbabilities(informationForWar.getAttack_name(),
                                informationForWar.getDefend_name());
                IntentPackage intentPackage_temp = new IntentPackage(information);
                Intent intent_temp = new Intent(SpecificFieldActivity.this,
                        SpecificFieldActivity.class);
                intent_temp.putExtra("currentInformation", intentPackage_temp);
                finish();
                overridePendingTransition(0,0);
                startActivity(intent_temp);
                overridePendingTransition(0,0);

            }
        });














    }

    public InformationForWar readFileWithProbabilities(String attacker, String defender) {
        InputStream inputStream = this.getResources().openRawResource(R.raw.probability_table);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitString = line.split(" ");
                if (attacker.equals(splitString[0]) && defender.equals(splitString[1])) {
                    return new InformationForWar(splitString[0], splitString[1],
                            splitString[2], splitString[3],
                            splitString[4], splitString[5]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Error reading file", Toast.LENGTH_LONG);
        return null;
    }
}