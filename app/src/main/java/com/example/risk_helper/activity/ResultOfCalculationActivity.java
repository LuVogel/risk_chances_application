package com.example.risk_helper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.risk_helper.R;
import com.example.risk_helper.intent_packages.CalculatedListView;
import com.example.risk_helper.adapter.CalculatedListViewAdapter;
import com.example.risk_helper.intent_packages.InformationForWar;
import com.example.risk_helper.intent_packages.IntentPackage;
import com.example.risk_helper.intent_packages.IntentPackageWar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ResultOfCalculationActivity extends AppCompatActivity {

    ImageButton goFromResultListBackToInputFieldButton;

    /**
     * creates ResultOfCalculationActivity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_of_calculation);

        // get intent, sent from InputField Activity (contains all user inputs)
        Intent intent = getIntent();
        IntentPackage intentPackage = (IntentPackage) intent.getSerializableExtra("count");
        int[] attackerArray = intentPackage.getAttacker();
        int[] defenderArray = intentPackage.getDefender();
        int attacker1 = attackerArray[0];
        int defender1 = defenderArray[0], defender2 = defenderArray[1],
                defender3 = defenderArray[2], defender4 = defenderArray[3],
                defender5 = defenderArray[4], defender6 = defenderArray[5];

        // information packages used to send to next activity
        InformationForWar informationField1, informationField2, informationField3, informationField4,
                informationField5, informationField6;
        // listViews needed for dynamic ListView in xml
        CalculatedListView calculatedListViewField1, calculatedListViewField2, calculatedListViewField3,
                calculatedListViewField4, calculatedListViewField5, calculatedListViewField6;

        // contains all listviews, which needed to be shown
        ArrayList<CalculatedListView> calculatedList = new ArrayList<CalculatedListView>();

        // first row in the next activity (table-header)
        InformationForWar headerForWar = new InformationForWar("#A",
                "#D", "surv. A",
                "surv. D", "Win A",
                "Win D");
        CalculatedListView headerForFields = new CalculatedListView(headerForWar);
        calculatedList.add(headerForFields);

        //check all defenders, if they are not equals zero, add them to our listviews
        // which needed to be shown
        if (defender1 != 0) {
            informationField1 = readFileWithProbabilities(attacker1, defender1);
            calculatedListViewField1 = new CalculatedListView(informationField1);
            calculatedList.add(calculatedListViewField1);
        }

        if (defender2 != 0) {
            informationField2 = readFileWithProbabilities(attacker1, defender2);
            calculatedListViewField2 = new CalculatedListView(informationField2);
            calculatedList.add(calculatedListViewField2);
        }
        if (defender3 != 0) {
            informationField3 = readFileWithProbabilities(attacker1, defender3);
            calculatedListViewField3 = new CalculatedListView(informationField3);
            calculatedList.add(calculatedListViewField3);
        }
        if (defender4 != 0) {
            informationField4 = readFileWithProbabilities(attacker1, defender4);
            calculatedListViewField4 = new CalculatedListView(informationField4);
            calculatedList.add(calculatedListViewField4);
        }
        if (defender5 != 0) {
            informationField5 = readFileWithProbabilities(attacker1, defender5);
            calculatedListViewField5 = new CalculatedListView(informationField5);
            calculatedList.add(calculatedListViewField5);
        }
        if (defender6 != 0) {
            informationField6 = readFileWithProbabilities(attacker1, defender6);
            calculatedListViewField6 = new CalculatedListView(informationField6);
            calculatedList.add(calculatedListViewField6);
        }

        goFromResultListBackToInputFieldButton = findViewById(R.id.return_from_result_list_to_input_field_button);

        //customAdapter, needed to show all our listviews on the screen
        CalculatedListViewAdapter calculatedListViewAdapter = new CalculatedListViewAdapter(this, calculatedList);
        ListView resultListView = findViewById(R.id.calculatedListView);
        resultListView.setAdapter(calculatedListViewAdapter);

        // if the a row is clicked, start next activity (SpecificFieldActivity) and send the
        // information from the clicked listView to the activity
       resultListView.setOnItemClickListener((parent, view, position, id) -> {
           CalculatedListView currentCalculatedView = (CalculatedListView) parent.getItemAtPosition(position);
           IntentPackageWar intentPackageWar = new IntentPackageWar(currentCalculatedView.getInformationForWar(), intentPackage);
           Intent intent_wars = new Intent(ResultOfCalculationActivity.this, SpecificFieldActivity.class);
           intent_wars.putExtra("currentInformation", intentPackageWar);
           startActivity(intent_wars);
       });

       // go back to InputFieldActivity
       goFromResultListBackToInputFieldButton.setOnClickListener(v -> finish());
    }

    /**
     * read filed, and search for current attacker/defender count
     * @param attacker current attacker count
     * @param defender current defender count
     * @return store the wanted line into an InformationForWar-IntentPackage
     */
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
                            splitString[4]+"%", splitString[5]+"%");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Error reading file", Toast.LENGTH_LONG);
        return null;
    }
}