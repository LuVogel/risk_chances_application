package com.example.risk_helper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.risk_helper.R;
import com.example.risk_helper.other.CalculatedListView;
import com.example.risk_helper.other.CalculatedListViewAdapter;
import com.example.risk_helper.other.InformationForWar;
import com.example.risk_helper.other.IntentPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ResultOfCalculationActivity extends AppCompatActivity {


    ImageButton returnToInputFieldActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_of_calculation);

        Intent intent = getIntent();
        IntentPackage intentPackage = (IntentPackage) intent.getSerializableExtra("count");
        int[] attackerArray = intentPackage.getAttacker();
        int[] defenderArray = intentPackage.getDefender();
        int attacker1 = attackerArray[0], attacker2 = attackerArray[1],
                attacker3 = attackerArray[2], attacker4 = attackerArray[3],
                attacker5 = attackerArray[4], attacker6 = attackerArray[5];
        int defender1 = defenderArray[0], defender2 = defenderArray[1],
                defender3 = defenderArray[2], defender4 = defenderArray[3],
                defender5 = defenderArray[4], defender6 = defenderArray[5];

        InformationForWar informationField1 = readFileWithProbabilities(attacker1, defender1);
        CalculatedListView calculatedListViewField1 = new CalculatedListView(informationField1);
        InformationForWar informationField2, informationField3, informationField4,
                informationField5, informationField6;
        CalculatedListView calculatedListViewField2, calculatedListViewField3,
                calculatedListViewField4, calculatedListViewField5, calculatedListViewField6;

        ArrayList<CalculatedListView> calculatedList = new ArrayList<CalculatedListView>();

        InformationForWar headerForWar = new InformationForWar("Attacker",
                "Defender", "remaining Attacker",
                "remaining Defender", "Probability Attacker Win",
                "Probability Defender Win");
        CalculatedListView headerForFields = new CalculatedListView(headerForWar);
        calculatedList.add(headerForFields);
        calculatedList.add(calculatedListViewField1);

        if (attacker2 != 0 && defender2 != 0) {
            informationField2 = readFileWithProbabilities(attacker2, defender2);
            calculatedListViewField2 = new CalculatedListView(informationField2);
            calculatedList.add(calculatedListViewField2);
        }
        if (attacker3 != 0 && defender3 != 0) {
            informationField3 = readFileWithProbabilities(attacker3, defender3);
            calculatedListViewField3 = new CalculatedListView(informationField3);
            calculatedList.add(calculatedListViewField3);
        }
        if (attacker4 != 0 && defender4 != 0) {
            informationField4 = readFileWithProbabilities(attacker4, defender4);
            calculatedListViewField4 = new CalculatedListView(informationField4);
            calculatedList.add(calculatedListViewField4);
        }
        if (attacker5 != 0 && defender5 != 0) {
            informationField5 = readFileWithProbabilities(attacker5, defender5);
            calculatedListViewField5 = new CalculatedListView(informationField5);
            calculatedList.add(calculatedListViewField5);
        }
        if (attacker6 != 0 && defender6 != 0) {
            informationField6 = readFileWithProbabilities(attacker6, defender6);
            calculatedListViewField6 = new CalculatedListView(informationField6);
            calculatedList.add(calculatedListViewField6);
        }


        returnToInputFieldActivity = findViewById(R.id.returnToCalculationActivity);

        CalculatedListViewAdapter calculatedListViewAdapter = new CalculatedListViewAdapter(this, calculatedList);
        ListView resultListView = findViewById(R.id.calculatedListView);
        resultListView.setAdapter(calculatedListViewAdapter);

       resultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               InformationForWar currentInformation =
                       (InformationForWar) parent.getItemAtPosition(position);
               IntentPackage intentPackage = new IntentPackage(currentInformation);
               Intent intent = new Intent(ResultOfCalculationActivity.this, SpecificFieldActivity.class);
               intent.putExtra("currentInformation", intentPackage);
               startActivity(intent);
           }
       });


        returnToInputFieldActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultOfCalculationActivity.super.onBackPressed();
            }
        });
    }

    public InformationForWar readFileWithProbabilities(int attacker, int defender) {
        InputStream inputStream = this.getResources().openRawResource(R.raw.probability_table);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitString = line.split(" ");
                if (attacker == Integer.parseInt(splitString[0]) && defender == Integer.parseInt(splitString[1])) {
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