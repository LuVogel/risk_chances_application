package com.example.risk_helper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.risk_helper.other.IntentPackage;
import com.example.risk_helper.R;

public class InputFieldActivity extends AppCompatActivity {


    EditText attack_field1_editText, defend_field1_editText, defend_field2_editText,
            defend_field3_editText, defend_field4_editText, defend_field5_editText,
            defend_field6_editText;
    Button calculate_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_field);
        attack_field1_editText = findViewById(R.id.attacker_text_view1);
        defend_field1_editText = findViewById(R.id.defender_text_view_1);
        defend_field2_editText = findViewById(R.id.defender_text_view_2);
        defend_field3_editText = findViewById(R.id.defender_text_view_3);
        defend_field4_editText = findViewById(R.id.defender_text_view_4);
        defend_field5_editText = findViewById(R.id.defender_text_view_5);
        defend_field6_editText = findViewById(R.id.defender_text_view_6);
        calculate_button = findViewById(R.id.calculate_button);

        calculate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int attacker_field1 = 0, defender_field1 = 0, defender_field2 = 0,
                        defender_field3 = 0, defender_field4 = 0, defender_field5 = 0, defender_field6 = 0;

                assert (attacker_field1 <=75) && (defender_field1 <=75) &&  (defender_field2 <=75)
                        && (defender_field3 <=75) &&  (defender_field4 <=75) &&
                        (defender_field5 <=75) &&  (defender_field6 <=75);


                //TODO: delete, just for testcase
                /**
                attack_field1_editText.setText("5");
                defend_field1_editText.setText("1");
                defend_field2_editText.setText("2");
                defend_field3_editText.setText("3");
                defend_field4_editText.setText("4");
                defend_field5_editText.setText("5");
                defend_field6_editText.setText("6");
                 */


                if (attack_field1_editText.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter at least Attacking Units for Field 1", Toast.LENGTH_LONG);
                } else if (defend_field1_editText.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter at least Defending Units for Field 1", Toast.LENGTH_LONG);
                } else {
                    try {
                        attacker_field1 = Integer.parseInt(attack_field1_editText.getText().toString());
                        defender_field1 = Integer.parseInt(defend_field1_editText.getText().toString());
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
                        Toast.makeText(getApplicationContext(), "Enter Attacking and Defending Units for Field 1", Toast.LENGTH_SHORT);
                    }


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

    }
}