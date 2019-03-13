package com.example.naman.namanapp.activities.DriverActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.naman.namanapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DistanceTravelledActivity extends AppCompatActivity {

    EditText etgetDistance;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_travelled);

//        etgetDistance = findViewById(R.id.etge)

        String distance = String.valueOf(etgetDistance.getText());

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Users");

        final String user_id = auth.getCurrentUser().getUid();

        databaseReference.child(user_id).child("distance").setValue(distance);

    }
}
