package com.example.networkingexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference databaseRef;

    private EditText emailInputField;
    private EditText passwordInputField;
    private EditText nameInputField;
    private Spinner majorSpinner;

    private Button addButton;
    private Button removeButton;
    private Button retrieveUsersButton;
    private TextView textView;

    private LinearLayout userDisplayLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseRef = FirebaseDatabase.getInstance().getReference("/users/");

        emailInputField = findViewById(R.id.email_input_field);
        passwordInputField = findViewById(R.id.password_input_field);
        nameInputField = findViewById(R.id.name_input_field);
        majorSpinner = findViewById(R.id.field_select_spinner);
        addButton = findViewById(R.id.addButton);
        removeButton = findViewById(R.id.deleteButton);
        retrieveUsersButton = findViewById(R.id.retrieveUsers);
        textView = findViewById(R.id.textView);
        userDisplayLayout = findViewById(R.id.user_display_layout);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String email = emailInputField.getText().toString().trim();
                String password = passwordInputField.getText().toString().trim();
                String name = nameInputField.getText().toString().trim();
                String major = majorSpinner.getSelectedItem().toString();
                if(!email.isEmpty() && !password.isEmpty() && !name.isEmpty() && !majorSpinner.getSelectedItem().toString().equals("Select..."))
                {
                    addUser(email, password, name, major);
                }
                }
            });

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userDisplayLayout.removeAllViews();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    User post = postSnapshot.getValue(User.class);
                    TextView userDataView = new TextView(userDisplayLayout.getContext());
                    userDataView.setText(post.email + " || " + post.password + " || " + post.name + " || " + post.major);
                    userDisplayLayout.addView(userDataView);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(userDisplayLayout.getContext(), "The read failed: " + databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    /*
     * This method is saving a new User to the Firebase Realtime Database
     * */
    private void addUser(String email, String password, String name, String major) {
        User user = new User(email, password, name, major);
        String id = user.formatEmail();

        databaseRef.child(id).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                emailInputField.setText("");
                passwordInputField.setText("");
                nameInputField.setText("");
                majorSpinner.setSelection(0);
                textView.setText("User added successfully");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                emailInputField.setText("");
                passwordInputField.setText("");
                nameInputField.setText("");
                majorSpinner.setSelection(0);
                textView.setText("Writing to database failed");
            }
        });

    }

}
