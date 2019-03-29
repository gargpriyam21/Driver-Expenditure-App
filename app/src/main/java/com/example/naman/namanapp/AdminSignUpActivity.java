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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminSignUpActivity extends AppCompatActivity {

    Button btnAdRegister;
    EditText etAdName, etAdEmail, etAdPassword, etAdCnfrmPassword;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_up);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Users");

        btnAdRegister = findViewById(R.id.btnAdRegister);
        etAdName = findViewById(R.id.etAdName);
        etAdEmail = findViewById(R.id.etAdEmail);
        etAdPassword = findViewById(R.id.etAdPassword);
        etAdCnfrmPassword = findViewById(R.id.etAdCnfrmpassword);

        btnAdRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = etAdName.getText().toString().trim();
                final String email = etAdEmail.getText().toString().trim();
                String password = etAdPassword.getText().toString().trim();
                String password2 = etAdCnfrmPassword.getText().toString().trim();


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

                if (!password.equals(password2)) {
                    Toast.makeText(getApplicationContext(), "Enter same password in ConfirmPassword!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }


                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(AdminSignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (!task.isSuccessful()) {
                                    Toast.makeText(AdminSignUpActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {

                                    final String user_id = auth.getCurrentUser().getUid();

                                    Driver driver = new Driver(user_id, name, email);

                                    databaseReference.child(user_id).setValue(driver);

                                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot snapshot) {
                                            databaseReference.child(user_id).child("usertype").setValue("admin");
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });

                                    Toast.makeText(AdminSignUpActivity.this, "Authentication successful",
                                            Toast.LENGTH_SHORT).show();

                                    Intent i = new Intent(AdminSignUpActivity.this, AdminActivity.class);
                                    //i.putExtra("CurrentUser", user_id);
                                    startActivity(i);
                                    finish();

                                }
                            }
                        });
            }
        });


    }
}
