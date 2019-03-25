package com.example.naman.namanapp.activities.DriverActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.naman.namanapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PassbookActivity extends AppCompatActivity {

    TextView tvName, tvAmount;
    RecyclerView rvExpenditure;

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;

    ArrayList<Expenditure> expenditures = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passbook);
    }
}
