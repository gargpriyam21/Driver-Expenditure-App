package com.example.naman.namanapp.activities.DriverActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.naman.namanapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DistanceTravelledActivity extends AppCompatActivity {

    EditText etgetDistance;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    Button btnDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_travelled);

        etgetDistance = findViewById(R.id.etgetDistance);
        btnDistance = findViewById(R.id.btnAddDistance);


        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Users");

        btnDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String distance = String.valueOf(etgetDistance.getText());

                if (TextUtils.isEmpty(distance)) {
                    Toast.makeText(getApplicationContext(), "Enter Reason!", Toast.LENGTH_SHORT).show();
                    return;
                }

                final String user_id = auth.getCurrentUser().getUid();

                databaseReference.child(user_id).child("distance").setValue(distance);
            }

        });


    }
}
