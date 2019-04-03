package com.example.naman.namanapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.naman.namanapp.Driver;
import com.example.naman.namanapp.R;
import com.example.naman.namanapp.activities.AdminFiles.DriverAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {

    RecyclerView rvDrivers;
    FirebaseDatabase database;
    DatabaseReference databaseReference, mDatabaseReference;
    FirebaseAuth auth;

    ArrayList<Driver> drivers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        auth = FirebaseAuth.getInstance();
//        database = FirebaseDatabase.getInstance();
//        databaseReference = database.getReference().child("Users");


        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
//
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Driver driver = postSnapshot.getValue(Driver.class);
//                    if(driver.getUsertype().equals("user")){
//                        drivers.add(driver);
//                    }

                    drivers.add(driver);

                }
//
//                String[] Drivers = new String[drivers.size()];
//
//                for (int i = 0; i < Drivers.length; i++) {
//                    Drivers[i] = drivers.get(i).getName();
//                }
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
//                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


        rvDrivers = findViewById(R.id.rvDrivers);
        rvDrivers.setLayoutManager(new LinearLayoutManager(this));

        final DriverAdapter usersAdapter = new DriverAdapter(drivers, this);

        //final DriverAdapter usersAdapter = new DriverAdapter(drivers,this);
        rvDrivers.setAdapter(usersAdapter);

    }

    @Override
    public void onBackPressed() {
        FirebaseAuth.getInstance().signOut();
        super.onBackPressed();
    }
}
