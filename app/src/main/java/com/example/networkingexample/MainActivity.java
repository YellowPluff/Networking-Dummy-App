package com.example.networkingexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private EditText firstNameInputField;
    private EditText lastNameInputField;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNameInputField = (EditText) findViewById(R.id.firstNameField);
        firstNameInputField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(firstNameInputField.getText().toString().equals("First Name"))
                {
                    firstNameInputField.setText("");
                }
            }
        });

        lastNameInputField = (EditText) findViewById(R.id.lastNameField);
        lastNameInputField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(lastNameInputField.getText().toString().equals("Last Name"))
                {
                    lastNameInputField.setText("");
                }
            }
        });

        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String first_name = firstNameInputField.getText().toString().trim();
                String last_name = lastNameInputField.getText().toString().trim();
                if(first_name.equals("First Name") || last_name.equals("Last Name"))
                {
                    //Error
                }
                else
                {
                    Spinner majorSpinner = (Spinner) findViewById(R.id.majorSpinner);
                    String major = majorSpinner.getSelectedItem().toString();

                    String displayString = "Hello, " + first_name + " " + last_name + ". Major: " + major;

                    Snackbar.make(view, displayString, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

    }
}
