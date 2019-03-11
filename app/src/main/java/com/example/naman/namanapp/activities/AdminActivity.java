package com.example.naman.namanapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.naman.namanapp.R;
import com.example.naman.namanapp.activities.AdminFiles.DriverAdapter;

public class AdminActivity extends AppCompatActivity {

    RecyclerView rvDrivers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        rvDrivers = findViewById(R.id.rvDrivers);
        rvDrivers.setLayoutManager(new LinearLayoutManager(this));

        final DriverAdapter usersAdapter = new DriverAdapter(this);
        rvDrivers.setAdapter(usersAdapter);

    }
}
