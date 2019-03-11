package com.example.naman.namanapp.activities.DriverActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.naman.namanapp.R;

public class AddExpenditureActivity extends AppCompatActivity {

    EditText etFuel, etToll, etPersonal, etMaintenance, etInsurance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenditure);

        etFuel = findViewById(R.id.etFuel);
        etToll = findViewById(R.id.etToll);
        etPersonal = findViewById(R.id.etPersonal);
        etMaintenance = findViewById(R.id.etMaintenance);
        etInsurance = findViewById(R.id.etInsurance);



    }
}
