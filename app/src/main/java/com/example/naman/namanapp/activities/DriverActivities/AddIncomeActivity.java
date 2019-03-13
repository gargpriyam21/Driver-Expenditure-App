package com.example.naman.namanapp.activities.DriverActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.naman.namanapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddIncomeActivity extends AppCompatActivity {

    EditText etgetIncome;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);

//        etgetIncome = findViewById(R.id.)

//        etgetDistance = findViewById(R.id.et)

        String distance = String.valueOf(etgetIncome.getText());

    }
}
