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
import android.widget.TextView;
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

//    public static final String first_name = "networkingapp.firstname";
//    public static final String last_name = "networkingapp.lastname";
//    public static final String major = "networkingapp.major";

    private EditText nameInputField;
    private EditText idInputField;
    private Spinner majorSpinner;
    private Button addButton;
    private Button removeButton;
    private TextView textView;

    //User class is not created yet
//    List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInputField = findViewById(R.id.nameField);
        idInputField = findViewById(R.id.idField);
        majorSpinner = findViewById(R.id.majorSpinner);
        addButton = findViewById(R.id.addButton);
        removeButton = findViewById(R.id.deleteButton);
        textView = findViewById(R.id.textView);

//        users = new ArrayList<>();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String name = nameInputField.getText().toString().trim();
                String studentId = idInputField.getText().toString().trim();
                String major = majorSpinner.getSelectedItem().toString();
                if(!name.isEmpty() && !studentId.isEmpty())
                {
                    textView.setText("Hello, " + name + " (" + studentId + "). Major: " + major);
                    int returnCode = addUser(name, studentId, major); //TODO
                    if(returnCode == 0)
                    {
                        //Resetting input fields
                        nameInputField.setText("");
                        idInputField.setText("");

                        //displaying a success toast
                        textView.setText("User added successfully");
                    }
                }
                }
            });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentId = idInputField.getText().toString().trim();
                if(!studentId.isEmpty())
                {
                    int returnCode = deleteUser(studentId);
                    if(returnCode == 0)
                    {
                        //Resetting input fields
                        nameInputField.setText("");
                        idInputField.setText("");

                        //displaying a success toast
                        textView.setText("User Deleted successfully");
                    }
                }
            }
        });
        }

    /*
     * This method is saving a new User to the Firebase Realtime Database
     * */
    private int addUser(String name, String studentId, String major) {
        //getting a unique id using push().getKey() method
        //it will create a unique id and we will use it as the Primary Key for our User
        DatabaseReference databaseUsers = FirebaseDatabase.getInstance().getReference("/Users_list");
        String id = databaseUsers.push().getKey();

        //creating an User Object
        User user = new User(name, studentId, major);

        databaseUsers.child(id).setValue(user);
//        databaseUsers.setValue(user2);
        return 0;
    }

    /*
     * This method is removing a User from the Firebase Realtime Database
     */
    private int deleteUser(String id) {
        //getting the specified User reference
        //TODO I'm not sure what "users" references
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference().child(id);

        //removing User
        dR.removeValue();

        //getting the tracks reference for the specified User
        DatabaseReference drTracks = FirebaseDatabase.getInstance().getReference().child(id);

        //removing all tracks
        drTracks.removeValue();

        return 0;
    }











    

//        listViewUsers.setOnItemClickListener(new AdapterView.OnItemClickListener()) {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                //getting the selected user
//                //User user = users.get(i);
//
//                //creating an intent
//                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
//
//                //putting User name and id to intent
//                intent.putExtra(first_name, firstNameInputField.getText().toString());
//                intent.putExtra(last_name, lastNameInputField.getText().toString());
//                intent.putExtra(major, majorSpinner.getSelectedItem().toString());
//                //starting the activity with intent
//                startActivity(intent);
//            }
//        });

//        listViewUsers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                User user = users.get(i);
//                showUpdateDeleteDialog(user.getUserId(), user.getUserName());
//                return true;
//            }
//        });

//    private void showUpdateDeleteDialog(final String userId, String userName) {
//
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = getLayoutInflater();
//        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
//        dialogBuilder.setView(dialogView);
//
//
//
//        dialogBuilder.setTitle(userName);
//        final AlertDialog b = dialogBuilder.create();
//        b.show();
//
//    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        //attaching value event listener
//        databaseArtists.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                //clearing the previous User list
//                users.clear();
//
//                //iterating through all the nodes
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    //getting User
//                    User user = postSnapshot.getValue(user.class);
//                    //adding User to the list
//                    users.add(user);
//                }
//
//                //creating adapter
//                UserList userAdapter = new UserList(MainActivity.this, users);
//                //attaching adapter to the listview
//                listViewUsers.setAdapter(userAdapter);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

}
