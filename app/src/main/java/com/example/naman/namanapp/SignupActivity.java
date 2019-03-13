package com.example.naman.namanapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.naman.namanapp.activities.AdminActivity;
import com.example.naman.namanapp.activities.DriverActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignupActivity extends AppCompatActivity {

    Button btnRegister;
    EditText etName, etEmail, etPassword, etCnfrmPassword, etCarName, etCarNo, etContact;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Users");

        etCarNo = findViewById(R.id.etCarNo);
        btnRegister = findViewById(R.id.btnRegister);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etCnfrmPassword = findViewById(R.id.etCnfrmpassword);
        etCarName = findViewById(R.id.etCarName);
        etContact = findViewById(R.id.etContact);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = etName.getText().toString().trim();
                final String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String password2 = etCnfrmPassword.getText().toString().trim();
                final String carname = etCarName.getText().toString().trim();
                final String carNo = etCarNo.getText().toString().trim();
                final String contact = etContact.getText().toString().trim();
                final String amount = "0";
                final String distance = "0";


                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Enter name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(carname)) {
                    Toast.makeText(getApplicationContext(), "Enter Car Name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(carNo)) {
                    Toast.makeText(getApplicationContext(), "Enter Car No!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(contact)) {
                    Toast.makeText(getApplicationContext(), "Enter Contact Number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(password2)) {
                    Toast.makeText(getApplicationContext(), "Enter same password in ConfirmPassword!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }


                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {

                                    final String user_id = auth.getCurrentUser().getUid();

                                    Driver driver = new Driver(user_id, name, email, carname, carNo, contact, amount, distance);

                                    databaseReference.child(user_id).setValue(driver);

                                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot snapshot) {
                                            if (snapshot.hasChild("usertype")) {
                                                databaseReference.child(user_id).child("usertype").setValue("admin");
                                            } else {
                                                databaseReference.child(user_id).child("usertype").setValue("user");
                                            }
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });

                                    Toast.makeText(SignupActivity.this, "Authentication successful",
                                            Toast.LENGTH_SHORT).show();

                                    if (databaseReference.child(user_id).child("usertype").getKey() == "user") {
                                        Intent i = new Intent(SignupActivity.this, DriverActivity.class);
                                        //i.putExtra("CurrentUser", user_id);
                                        startActivity(i);
                                        finish();

                                    } else if (databaseReference.child(user_id).child("usertype").getKey() == "admin") {
                                        Intent i = new Intent(SignupActivity.this, AdminActivity.class);
                                        //i.putExtra("CurrentUser", user_id);
                                        startActivity(i);
                                        finish();

                                    }

                                }
                            }
                        });
            }
        });


    }
}
