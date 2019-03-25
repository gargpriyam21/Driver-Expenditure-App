package com.example.naman.namanapp.activities.DriverActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.naman.namanapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddExpenditureActivity extends AppCompatActivity {

    EditText etFuel, etToll, etPersonal, etMaintenance, etInsurance;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    Button btnExpenditure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenditure);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Users");

        etFuel = findViewById(R.id.etFuel);
        etToll = findViewById(R.id.etToll);
        etPersonal = findViewById(R.id.etPersonal);
        etMaintenance = findViewById(R.id.etMaintenance);
        etInsurance = findViewById(R.id.etInsurance);
        btnExpenditure = findViewById(R.id.btnExpenditure);


        btnExpenditure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fuel = etFuel.getText().toString();
                String toll = etToll.getText().toString();
                String personal = etToll.getText().toString();
                String mainteneance = etMaintenance.getText().toString();
                String insurance = etInsurance.getText().toString();


                Date curDate = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
                final String datetime = dateFormat.format(curDate);


                if (TextUtils.isEmpty(fuel)) {
                    fuel = String.valueOf(0);
                }
                if (TextUtils.isEmpty(toll)) {
                    toll = String.valueOf(0);
                }
                if (TextUtils.isEmpty(personal)) {
                    personal = String.valueOf(0);
                }
                if (TextUtils.isEmpty(mainteneance)) {
                    mainteneance = String.valueOf(0);
                }
                if (TextUtils.isEmpty(insurance)) {
                    insurance = String.valueOf(0);
                }

                int totalExp = Integer.parseInt(fuel) + Integer.parseInt(toll) + Integer.parseInt(personal) + Integer.parseInt(mainteneance) + Integer.parseInt(insurance);
                String total = String.valueOf(totalExp);

                final String user_id = auth.getCurrentUser().getUid();

                Expenditure expenditure = new Expenditure(datetime, fuel, toll, personal, mainteneance, insurance, total);

                databaseReference.child(user_id).child("Expenditure").setValue(expenditure);

            }
        });


    }
}
