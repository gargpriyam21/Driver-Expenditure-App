package com.example.naman.namanapp.activities.AdminFiles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.naman.namanapp.R;

public class AdminDriverActivity extends AppCompatActivity {

    Button btnPassbook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_driver);

        btnPassbook = findViewById(R.id.btnPassbook);

        btnPassbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}
