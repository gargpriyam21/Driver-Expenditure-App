package com.example.naman.namanapp.activities.DriverFiles;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.naman.namanapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddExpenditureActivity extends AppCompatActivity {

    EditText etgetExpenditure, etgetReason;
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

        etgetExpenditure = findViewById(R.id.etgetExpenditure);
        etgetReason = findViewById(R.id.etgetReason);
        btnExpenditure = findViewById(R.id.btnAddExpenditure);

        btnExpenditure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String amount = etgetExpenditure.getText().toString();
                final String reason = etgetReason.getText().toString();

                Date curDate = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
                final String datetime = dateFormat.format(curDate);


                if (TextUtils.isEmpty(amount)) {
                    Toast.makeText(getApplicationContext(), "Enter Amount!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(reason)) {
                    Toast.makeText(getApplicationContext(), "Enter Reason!", Toast.LENGTH_SHORT).show();
                    return;
                }

                final String user_id = auth.getCurrentUser().getUid();

                Expenditure expenditure = new Expenditure(datetime, amount, reason);

                databaseReference.child(user_id).child("Expenditure").push().setValue(expenditure);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String DBamount = dataSnapshot.child(user_id).child("amount").getValue(String.class);
                        int amt = Integer.parseInt(DBamount) - Integer.parseInt(amount);
                        databaseReference.child(auth.getCurrentUser().getUid()).child("amount").setValue(String.valueOf(amt));

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });

                etgetExpenditure.setText(null);
                etgetReason.setText(null);

            }
        });

    }
}
