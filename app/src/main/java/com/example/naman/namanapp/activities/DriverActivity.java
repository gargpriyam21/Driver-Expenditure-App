package com.example.naman.namanapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.naman.namanapp.R;
import com.example.naman.namanapp.activities.DriverActivities.AddExpenditureActivity;
import com.example.naman.namanapp.activities.DriverActivities.AddIncomeActivity;
import com.example.naman.namanapp.activities.DriverActivities.DistanceTravelledActivity;
import com.example.naman.namanapp.activities.DriverActivities.PassbookActivity;

public class DriverActivity extends AppCompatActivity {

    ImageView ivImg;
    TextView tvName, tvCarNo, tvCarName;
    Button btnAddInc, btnAddExp, btnDistTravel, btnPassbook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        ivImg = findViewById(R.id.ivImg);
        tvCarName = findViewById(R.id.tvCarName);
        tvCarNo = findViewById(R.id.tvCarNo);
        tvName = findViewById(R.id.tvName);
        btnAddExp = findViewById(R.id.btnAddExp);
        btnAddInc = findViewById(R.id.btnDistTravel);
        btnPassbook = findViewById(R.id.btnPassbook);
        btnDistTravel = findViewById(R.id.btnDistTravel);

        View.OnClickListener onButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = null;
                switch (view.getId()) {
                    case R.id.btnAddExp:
                        i = new Intent(DriverActivity.this, AddExpenditureActivity.class);
                        break;
                    case R.id.btnAddInc:
                        i = new Intent(DriverActivity.this, AddIncomeActivity.class);
                        break;
                    case R.id.btnDistTravel:
                        i = new Intent(DriverActivity.this, DistanceTravelledActivity.class);
                        break;
                    case R.id.btnPassbook:
                        i = new Intent(DriverActivity.this, PassbookActivity.class);
                        break;
                }
                startActivity(i);

            }
        };

        btnAddInc.setOnClickListener(onButtonClickListener);
        btnAddExp.setOnClickListener(onButtonClickListener);
        btnDistTravel.setOnClickListener(onButtonClickListener);
        btnPassbook.setOnClickListener(onButtonClickListener);

    }
}
