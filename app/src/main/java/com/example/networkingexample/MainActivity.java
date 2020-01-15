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
    private Spinner majorSpinner;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNameInputField = (EditText) findViewById(R.id.firstNameField);
        lastNameInputField = (EditText) findViewById(R.id.lastNameField);
        majorSpinner = (Spinner) findViewById(R.id.majorSpinner);

        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String first_name = firstNameInputField.getText().toString().trim();
                String last_name = lastNameInputField.getText().toString().trim();
                String major = majorSpinner.getSelectedItem().toString();
                if(!first_name.isEmpty() && !last_name.isEmpty())
                {
                    String displayString = "Hello, " + first_name + " " + last_name + ". Major: " + major;
                    Snackbar.make(view, displayString, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });

    }
}
