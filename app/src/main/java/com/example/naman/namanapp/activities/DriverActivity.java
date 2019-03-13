package com.example.naman.namanapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.naman.namanapp.Driver;
import com.example.naman.namanapp.R;
import com.example.naman.namanapp.activities.DriverActivities.AddExpenditureActivity;
import com.example.naman.namanapp.activities.DriverActivities.AddIncomeActivity;
import com.example.naman.namanapp.activities.DriverActivities.DistanceTravelledActivity;
import com.example.naman.namanapp.activities.DriverActivities.PassbookActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DriverActivity extends AppCompatActivity {

    ImageView ivImg;
    TextView tvName, tvCarNo, tvCarName;
    Button btnAddInc, btnAddExp, btnDistTravel, btnPassbook;

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;

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

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Users");
        mAuth = FirebaseAuth.getInstance();


//        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
//
//        mDatabaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    Upload upload = postSnapshot.getValue(Upload.class);
//                    uploadList.add(upload);
//                }
//
//                String[] uploads = new String[uploadList.size()];
//
//                for (int i = 0; i < uploads.length; i++) {
//                    uploads[i] = uploadList.get(i).getName();
//                }
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
//                listView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot postsnapshot = (DataSnapshot) dataSnapshot.getChildren();
                Driver driver = postsnapshot.getValue(Driver.class);

                tvName.setText(driver.getName());
                tvCarName.setText(driver.getCarname());
                tvCarNo.setText(driver.getCarno());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                //TODO generate Error Message
            }
        });

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
