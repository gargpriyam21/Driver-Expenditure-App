package com.example.naman.namanapp.activities.AdminFiles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.naman.namanapp.R;
import com.example.naman.namanapp.activities.DriverActivities.PassbookActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminDriverActivity extends AppCompatActivity {


    TextView tvName, tvCarName, tvCarNo, tvContact;
    Button btnPassbook;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_driver);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Users");
        auth = FirebaseAuth.getInstance();

        tvName = findViewById(R.id.tvNameAD);
        tvCarName = findViewById(R.id.tvCarNameAD);
        tvCarNo = findViewById(R.id.tvCarNoAD);
        tvContact = findViewById(R.id.tvContactAD);

        Intent i = getIntent();
        String Uid = i.getStringExtra("UserId");

        databaseReference.child(Uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot postsnapshot = (DataSnapshot) dataSnapshot.getChildren();
                com.example.naman.namanapp.Driver driver = postsnapshot.getValue(com.example.naman.namanapp.Driver.class);

                tvName.setText(driver.getName());
                tvCarName.setText(driver.getCarname());
                tvCarNo.setText(driver.getCarno());
                tvContact.setText(driver.getContact());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                //TODO generate Error Message
            }
        });


        btnPassbook = findViewById(R.id.btnPassbook);

        btnPassbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent();
                startActivity(new Intent(AdminDriverActivity.this, PassbookActivity.class));


            }
        });


    }
}
