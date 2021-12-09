package com.example.risk_helper.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.risk_helper.intent_packages.IntentPackage;
import com.example.risk_helper.R;

public class InputFieldActivity extends AppCompatActivity {

    // all fields where use can input attacker, resp. defender count
    EditText attack_field1_editText, defend_field1_editText, defend_field2_editText,
            defend_field3_editText, defend_field4_editText, defend_field5_editText,
            defend_field6_editText;
    // button to go to the next activity
    Button calculate_button;
    // button to go back to main activity
    ImageButton goFromInputFieldToMainButton;

    /**
     * creates input-field activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_field);
        // find fields in defined in xml
        attack_field1_editText = findViewById(R.id.attacker_text_view1);
        defend_field1_editText = findViewById(R.id.defender_text_view_1);
        defend_field2_editText = findViewById(R.id.defender_text_view_2);
        defend_field3_editText = findViewById(R.id.defender_text_view_3);
        defend_field4_editText = findViewById(R.id.defender_text_view_4);
        defend_field5_editText = findViewById(R.id.defender_text_view_5);
        defend_field6_editText = findViewById(R.id.defender_text_view_6);
        calculate_button = findViewById(R.id.calculate_button);
        goFromInputFieldToMainButton = findViewById(R.id.return_from_input_field_to_main_button);

        // if calculate button is clicked, check which fields got a user input
        calculate_button.setOnClickListener(v -> {
            int attacker_field1 = 0, defender_field1 = 0, defender_field2 = 0,
                    defender_field3 = 0, defender_field4 = 0, defender_field5 = 0, defender_field6 = 0;

            // attacker field must have userInput, show alertDialog if not
            if (attack_field1_editText.getText().toString().isEmpty()) {
                AlertDialog.Builder dialog = buildDialog(getResources().getString(R.string.alertNoAttacker));
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            } else {
                try {
                    // since attacker field is not empty, get entered text on attacker-field
                    attacker_field1 = Integer.parseInt(attack_field1_editText.getText().toString());
                    // check all defender fields if they have input, if they have: store input into
                    // variable, if not: set to zero
                    if (defend_field1_editText.getText().toString().isEmpty()) {
                        defender_field1 = 0;
                    } else {
                        defender_field1 = Integer.parseInt(defend_field1_editText.getText().toString());
                    }
                    if (defend_field2_editText.getText().toString().isEmpty()) {
                        defender_field2 = 0;
                    } else {
                        defender_field2 = Integer.parseInt(defend_field2_editText.getText().toString());
                    }
                    if (defend_field3_editText.getText().toString().isEmpty()) {
                        defender_field3 = 0;
                    } else {
                        defender_field3 = Integer.parseInt(defend_field3_editText.getText().toString());
                    }
                    if (defend_field4_editText.getText().toString().isEmpty()) {
                        defender_field4 = 0;
                    } else {
                        defender_field4 = Integer.parseInt(defend_field4_editText.getText().toString());
                    }
                    if (defend_field5_editText.getText().toString().isEmpty()) {
                        defender_field5 = 0;
                    } else {
                        defender_field5 = Integer.parseInt(defend_field5_editText.getText().toString());
                    }
                    if (defend_field6_editText.getText().toString().isEmpty()) {
                        defender_field6 = 0;
                    } else {
                        defender_field6 = Integer.parseInt(defend_field6_editText.getText().toString());
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                boolean somethingOver75 = false;

                // check all fields if the entered value exceeds 75,
                // if true: change back to 75 and bool = true
                if (attacker_field1 > 75) {
                    attacker_field1 = 75;
                    attack_field1_editText.setText("75");
                    somethingOver75 = true;
                }
                if (defender_field1 > 75) {
                    defender_field1 = 75;
                    defend_field1_editText.setText("75");
                    somethingOver75 = true;
                }
                if (defender_field2 > 75) {
                    defender_field2 = 75;
                    defend_field2_editText.setText("75");
                    somethingOver75 = true;
                }
                if (defender_field3 > 75) {
                    defender_field3 = 75;
                    defend_field3_editText.setText("75");
                    somethingOver75 = true;
                }
                if (defender_field4 > 75) {
                    defender_field4 = 75;
                    defend_field4_editText.setText("75");
                    somethingOver75 = true;
                }
                if (defender_field5 > 75) {
                    defender_field5 = 75;
                    defend_field5_editText.setText("75");
                    somethingOver75 = true;
                }
                if (defender_field6 > 75) {
                    defender_field6 = 75;
                    defend_field6_editText.setText("75");
                    somethingOver75 = true;
                }
                // if bool == true, show alertDialog that input was changed to 75
                if (somethingOver75) {
                    AlertDialog.Builder dialog = buildDialog(getResources().getString(R.string.alertOver75));
                    AlertDialog alertDialog = dialog.create();
                    alertDialog.show();
                } else if (defender_field1 == 0 && defender_field2 == 0 && defender_field3 == 0 &&
                        defender_field4 == 0 && defender_field5 == 0 && defender_field6 == 0) {
                    // if all defender fields are equals zero (no user-input on defender-fields)
                    // show alertDialog, at least one fields must have an input
                    AlertDialog.Builder dialog = buildDialog(getResources().getString(R.string.alertNoDefender));
                    AlertDialog alertDialog = dialog.create();
                    alertDialog.show();

                } else {
                    // no alertDialog happened, every userInput was fine, store values into an
                    // intent-package, and send them to next activity: start activity
                    // ResultOfCalculation
                    int[] attackerArray = new int[]{attacker_field1};
                    int[] defenderArray = new int[]{defender_field1,defender_field2,
                            defender_field3, defender_field4, defender_field5, defender_field6};

                    Intent intent = new Intent(InputFieldActivity.this, ResultOfCalculationActivity.class);
                    IntentPackage intentPackage = new IntentPackage(attackerArray, defenderArray);
                    intent.putExtra("count", intentPackage);
                    startActivity(intent);
                }

            }
        });

        // if return button is pressed, go back to main activity
        goFromInputFieldToMainButton.setOnClickListener(v -> {
            finish();
        });

    }

    /**
     * build an alertDialog depending on error (String - text)
     * @param text error
     * @return AlertDialog.Builder (stored information how to build the dialog)
     */
    public AlertDialog.Builder buildDialog(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(InputFieldActivity.this);
        builder.setMessage(text).setTitle(R.string.error);

        builder.setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder;

    }
}