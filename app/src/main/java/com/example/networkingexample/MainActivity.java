package com.example.networkingexample;

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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String first_name = "networkingapp.firstname";
    public static final String last_name = "networkingapp.lastname";
    public static final String major = "networkingapp.major";

    private EditText firstNameInputField;
    private EditText lastNameInputField;
    private Spinner majorSpinner;
    private Button submitButton;

    //User class is not created yet
    List<User> users;

    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseUsers = FirebaseDatabase.getInstance().getReference("users");

        firstNameInputField = (EditText) findViewById(R.id.firstNameField);
        lastNameInputField = (EditText) findViewById(R.id.lastNameField);
        majorSpinner = (Spinner) findViewById(R.id.majorSpinner);

        submitButton = (Button) findViewById(R.id.submitButton);
        users = new ArrayList<>();

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
                    addUser();
                }
                }
            });
        }

        /*listViewUsers.setOnItemClickListener(new AdapterView.OnItemClickListener()) {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //getting the selected user
                //User user = users.get(i);

                //creating an intent
                Intent intent = new Intent(getApplicationContext(), UserActivity.class);

                //putting artist name and id to intent
                intent.putExtra(first_name, firstNameInputField.getText().toString());
                intent.putExtra(last_name, lastNameInputField.getText().toString());
                intent.putExtra(major, majorSpinner.getSelectedItem().toString());
                //starting the activity with intent
                startActivity(intent);
            }
        });

        listViewUsers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                User user = users.get(i);
                showUpdateDeleteDialog(user.getUserId(), user.getUserName());
                return true;
            }
        });

    }*/
    /*private void showUpdateDeleteDialog(final String artistId, String artistName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);



        dialogBuilder.setTitle(userName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

    }

    private boolean deleteUser(String id) {
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("users").child(id);

        //removing artist
        dR.removeValue();

        //getting the tracks reference for the specified artist
        DatabaseReference drTracks = FirebaseDatabase.getInstance().getReference("tracks").child(id);

        //removing all tracks
        drTracks.removeValue();
        Toast.makeText(getApplicationContext(), "User Deleted", Toast.LENGTH_LONG).show();

        return true;
    }*/

    /*@Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                users.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    User user = postSnapshot.getValue(user.class);
                    //adding artist to the list
                    users.add(user);
                }

                //creating adapter
                UserList userAdapter = new UserList(MainActivity.this, users);
                //attaching adapter to the listview
                listViewUsers.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/



    /*
     * This method is saving a new artist to the
     * Firebase Realtime Database
     * */
    private void addUser() {
        //getting the values to save
        String firstname = firstNameInputField.getText().toString().trim();
        String lastname = lastNameInputField.getText().toString().trim();
        String major = majorSpinner.getSelectedItem().toString();
        String name = firstname + " " + lastname;

        //checking if the value is provided
        if (!TextUtils.isEmpty(name)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = databaseUsers.push().getKey();

            //creating an User Object
            //User user = new User(firstname, lastname, major);

            //Saving the Artist
            User user = new User();
            user.firstName = firstname;
            user.lastName = lastname;
            user.major = major;
            databaseUsers.child(id).setValue(user);
            //setting edittext to blank again
            firstNameInputField.setText("");

            //displaying a success toast
            Toast.makeText(this, "User added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }

}
