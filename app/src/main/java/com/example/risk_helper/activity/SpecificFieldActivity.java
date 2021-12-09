package com.example.risk_helper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.risk_helper.R;
import com.example.risk_helper.intent_packages.InformationForWar;
import com.example.risk_helper.intent_packages.IntentPackage;
import com.example.risk_helper.intent_packages.IntentPackageWar;
import com.example.risk_helper.intent_packages.IntentPackageWin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SpecificFieldActivity extends AppCompatActivity {

    ImageButton goFromSpecificFieldToResultButton, subAttackOne, subDefendOne, subBothOne,
            subAttackTwo, subDefendTwo;
    TextView attackerSpecificField, defenderSpecificField, attackerWinSpecificField,
            defenderWinSpecificField, remainingAttackerSpecificField, remainingDefenderSpecificField;

    /**
     * start SpecificFieldActivity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_field);
        // receive information from previous activity
        Intent intent = getIntent();
        IntentPackageWar intentPackageWar1 =
                (IntentPackageWar) intent.getSerializableExtra("currentInformation");
        // intent has two intentPackages inside: informationForWar and
        // intentPackageForWar (stored attacker, defender)
        InformationForWar informationForWar = intentPackageWar1.getInformationForWar();
        IntentPackage intentPackageForWar = intentPackageWar1.getIntentPackageFromWar();
        int attacker = Integer.parseInt(informationForWar.getAttack_name());
        int defender = Integer.parseInt(informationForWar.getDefend_name());

        // find views from xml
        attackerSpecificField = findViewById(R.id.attacker_specific_field);
        defenderSpecificField = findViewById(R.id.defender_specific_field);
        goFromSpecificFieldToResultButton = findViewById(R.id.return_from_specific_field_to_field_list_button);
        attackerWinSpecificField = findViewById(R.id.attacker_win_specific_field);
        defenderWinSpecificField = findViewById(R.id.defender_win_specific_field);
        remainingAttackerSpecificField = findViewById(R.id.remaining_attacker_specific_field);
        remainingDefenderSpecificField = findViewById(R.id.remaining_defender_specific_field);
        subAttackOne = findViewById(R.id.subAttackOne);
        subAttackTwo = findViewById(R.id.subAttackTwo);
        subDefendOne = findViewById(R.id.subDefendOne);
        subDefendTwo = findViewById(R.id.subDefendTwo);
        subBothOne = findViewById(R.id.subEachOne);

        // set text, information received from intent
        attackerSpecificField.setText(informationForWar.getAttack_name());
        defenderSpecificField.setText(informationForWar.getDefend_name());
        remainingAttackerSpecificField.setText(informationForWar.getRemaining_att_name());
        remainingDefenderSpecificField.setText(informationForWar.getRemaining_def_name());
        attackerWinSpecificField.setText(informationForWar.getAtt_win_name());
        defenderWinSpecificField.setText(informationForWar.getDef_win_name());

        // Show Buttons depending on current state
        // starting with all buttons invisible
        // buttons are needed to change values from attacker
        // and defender and start the activity again
        subDefendTwo.setVisibility(View.INVISIBLE);
        subDefendOne.setVisibility(View.INVISIBLE);
        subBothOne.setVisibility(View.INVISIBLE);
        subAttackTwo.setVisibility(View.INVISIBLE);
        subAttackOne.setVisibility(View.INVISIBLE);

        if (attacker == 1 && defender == 1) {
            subAttackOne.setVisibility(View.VISIBLE);
            subDefendOne.setVisibility(View.VISIBLE);
        }
        if (attacker == 2 && defender == 1) {
            subAttackOne.setVisibility(View.VISIBLE);
            subDefendOne.setVisibility(View.VISIBLE);

        } else if (attacker >= 3 && defender == 1) {
            subAttackOne.setVisibility(View.VISIBLE);
            subDefendOne.setVisibility(View.VISIBLE);
        } else if (attacker == 1 && defender >= 2) {
            subDefendOne.setVisibility(View.VISIBLE);
            subAttackOne.setVisibility(View.VISIBLE);
        } else if (attacker == 2 && defender >= 2) {
            subBothOne.setVisibility(View.VISIBLE);
            subDefendTwo.setVisibility(View.VISIBLE);
            subAttackTwo.setVisibility(View.VISIBLE);
        } else if (attacker >= 3 && defender >= 2) {
            subBothOne.setVisibility(View.VISIBLE);
            subAttackTwo.setVisibility(View.VISIBLE);
            subDefendTwo.setVisibility(View.VISIBLE);
        }

        // attacker lost one unit: start activity again, but send new intentPackages
        // (with information for attacker-1, defender)
        // check if defender has won: if so, start WinningActivity
        subAttackOne.setOnClickListener(v -> {
            if (Integer.parseInt(informationForWar.getAttack_name())-1 == 0) {
                //defender has won!
                Intent intent1 = new Intent(SpecificFieldActivity.this, WinningActivity.class);
                IntentPackageWin intentPackageWin = new IntentPackageWin("Defender");
                intent1.putExtra("winner", intentPackageWin);
                startActivity(intent1);
            } else {
                String currentAttacker = String.valueOf(Integer.parseInt(informationForWar.getAttack_name())-1);
                InformationForWar information =
                        readFileWithProbabilities(currentAttacker,
                                informationForWar.getDefend_name());
                IntentPackageWar intentPackage_temp = new IntentPackageWar(information, intentPackageForWar);
                Intent intent_temp = new Intent(SpecificFieldActivity.this,
                        SpecificFieldActivity.class);
                intent_temp.putExtra("currentInformation", intentPackage_temp);
                finish();
                overridePendingTransition(0,0);
                startActivity(intent_temp);
                overridePendingTransition(0,0);
            }


        });

        // attacker lost two units: start activity again, but send new intentPackages
        // (with information for attacker-2, defender)
        // check if defender has won: if so, start WinningActivity
        subAttackTwo.setOnClickListener(v -> {
            if (Integer.parseInt(informationForWar.getAttack_name())-2 == 0) {
                //defender has won!
                Intent intent1 = new Intent(SpecificFieldActivity.this, WinningActivity.class);
                IntentPackageWin intentPackageWin = new IntentPackageWin("Defender");
                intent1.putExtra("winner", intentPackageWin);
                startActivity(intent1);
            } else {
                String currentAttacker = String.valueOf(Integer.parseInt(informationForWar.getAttack_name())-2);
                InformationForWar information =
                        readFileWithProbabilities(currentAttacker,
                                informationForWar.getDefend_name());
                IntentPackageWar intentPackageWar = new IntentPackageWar(information, intentPackageForWar);
                Intent intent1 = new Intent(SpecificFieldActivity.this,
                        SpecificFieldActivity.class);
                intent1.putExtra("currentInformation", intentPackageWar);
                finish();
                overridePendingTransition(0,0);
                startActivity(intent1);
                overridePendingTransition(0,0);
            }

        });

        // attacker and defender lost each one units: start activity again, but send new intentPackages
        // (with information for attacker-1, defender-1)
        // check if attacker or defender has won: if so, start WinningActivity
        subBothOne.setOnClickListener(v -> {
            if (Integer.parseInt(informationForWar.getDefend_name())-1 == 0) {
                //attacker has won!
                Intent intent1 = new Intent(SpecificFieldActivity.this, WinningActivity.class);
                IntentPackageWin intentPackageWin = new IntentPackageWin("Attacker");
                intent1.putExtra("winner", intentPackageWin);
                startActivity(intent1);
            } else if (Integer.parseInt(informationForWar.getAttack_name())-1 == 0) {
                //defender has won!
                Intent intent1 = new Intent(SpecificFieldActivity.this, WinningActivity.class);
                IntentPackageWin intentPackageWin = new IntentPackageWin("Defender");
                intent1.putExtra("winner", intentPackageWin);
                startActivity(intent1);
            } else {
                String currentAttacker = String.valueOf(Integer.parseInt(informationForWar.getAttack_name())-1);
                String currentDefender = String.valueOf(Integer.parseInt(informationForWar.getDefend_name())-1);
                InformationForWar information =
                        readFileWithProbabilities(currentAttacker, currentDefender);
                IntentPackageWar intentPackageWar = new IntentPackageWar(information, intentPackageForWar);
                Intent intent1 = new Intent(SpecificFieldActivity.this,
                        SpecificFieldActivity.class);
                intent1.putExtra("currentInformation", intentPackageWar);
                finish();
                overridePendingTransition(0,0);
                startActivity(intent1);
                overridePendingTransition(0,0);
            }

        });

        // defender lost one unit: start activity again, but send new intentPackages
        // (with information for attacker, defender-1)
        // check if attacker has won: if so, start WinningActivity
        subDefendOne.setOnClickListener(v -> {
            if (Integer.parseInt(informationForWar.getDefend_name())-1 == 0) {
                //attacker has won!
                Intent intent1 = new Intent(SpecificFieldActivity.this, WinningActivity.class);
                IntentPackageWin intentPackageWin = new IntentPackageWin("Attacker");
                intent1.putExtra("winner", intentPackageWin);
                startActivity(intent1);
            } else {
                String currentDefender = String.valueOf(Integer.parseInt(informationForWar.getDefend_name())-1);
                InformationForWar information =
                        readFileWithProbabilities(informationForWar.getAttack_name(),
                                currentDefender);
                IntentPackageWar intentPackageWar = new IntentPackageWar(information, intentPackageForWar);
                Intent intent1 = new Intent(SpecificFieldActivity.this,
                        SpecificFieldActivity.class);
                intent1.putExtra("currentInformation", intentPackageWar);
                finish();
                overridePendingTransition(0,0);
                startActivity(intent1);
                overridePendingTransition(0,0);
            }

        });

        // defender lost two units: start activity again, but send new intentPackages
        // (with information for attacker, defender-2)
        // check if attacker has won: if so, start WinningActivity
        subDefendTwo.setOnClickListener(v -> {
            if (Integer.parseInt(informationForWar.getDefend_name())-2 == 0) {
                //attacker has won!
                Intent intent1 = new Intent(SpecificFieldActivity.this, WinningActivity.class);
                IntentPackageWin intentPackageWin = new IntentPackageWin("Attacker");
                intent1.putExtra("winner", intentPackageWin);
                startActivity(intent1);
            } else {
                String currentDefender = String.valueOf(Integer.parseInt(informationForWar.getDefend_name())-2);
                InformationForWar information =
                        readFileWithProbabilities(informationForWar.getAttack_name(),
                                currentDefender);
                IntentPackageWar intentPackageWar = new IntentPackageWar(information, intentPackageForWar);
                Intent intent1 = new Intent(SpecificFieldActivity.this,
                        SpecificFieldActivity.class);
                intent1.putExtra("currentInformation", intentPackageWar);
                finish();
                overridePendingTransition(0,0);
                startActivity(intent1);
                overridePendingTransition(0,0);
            }

        });

        goFromSpecificFieldToResultButton.setOnClickListener(v -> {
            Intent backIntent = new Intent(SpecificFieldActivity.this,
                    ResultOfCalculationActivity.class);
            intent.putExtra("count", intentPackageForWar);
            finish();
            overridePendingTransition(0,0);
            startActivity(backIntent);
            overridePendingTransition(0,0);
        });


    }

    /**
     * read file given state (inputs)
     * @param attacker current attacker
     * @param defender current defender
     * @return InformationForWar-IntentPackage with all information needed to
     * show for current attacker/defender
     */
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
                            splitString[4] + "%", splitString[5] + "%");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Error reading file", Toast.LENGTH_LONG);
        return null;
    }
}