package com.example.naman.namanapp.activities.AdminFiles;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.naman.namanapp.R;
import com.example.naman.namanapp.activities.DriverFiles.Expenditure;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminDriverActivity extends AppCompatActivity {

    ArrayList<Expenditure> Adminexpenditures = new ArrayList<>();
    TextView tvName, tvCarName, tvCarNo, tvContact;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    RecyclerView rvAdminPassExp;

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

                tvName.setText(dataSnapshot.child("name").getValue(String.class));
                tvCarName.setText(dataSnapshot.child("carname").getValue(String.class));
                tvCarNo.setText(dataSnapshot.child("carno").getValue(String.class));
                tvContact.setText(dataSnapshot.child("contact").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                //TODO generate Error Message
            }
        });


        auth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(Uid).child("Expenditure");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Expenditure expenditure = postSnapshot.getValue(Expenditure.class);
                    Adminexpenditures.add(expenditure);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        rvAdminPassExp = findViewById(R.id.rvAdminPassExp);
        rvAdminPassExp.setLayoutManager(new LinearLayoutManager(this));

        final AdminPassbookAdapter passbookAdapter = new AdminPassbookAdapter(this, Adminexpenditures);

        rvAdminPassExp.setAdapter(passbookAdapter);


    }
}

class AdminPassbookAdapter extends RecyclerView.Adapter<AdminPassbookAdapter.AdminExpenditureViewHolder> {

    Context context;
    ArrayList<Expenditure> Adminexpenditures;

    public AdminPassbookAdapter(Context context, ArrayList<Expenditure> expenditures) {
        this.context = context;
        this.Adminexpenditures = expenditures;
    }

    @Override
    public AdminPassbookAdapter.AdminExpenditureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.list_item_admin_expenditure, parent, false);

        return new AdminPassbookAdapter.AdminExpenditureViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdminPassbookAdapter.AdminExpenditureViewHolder holder, int position) {
        holder.bindView(Adminexpenditures.get(position));

    }

    @Override
    public int getItemCount() {
        return Adminexpenditures.size();
    }

    class AdminExpenditureViewHolder extends RecyclerView.ViewHolder {

        TextView tvAdminDatetime, tvAdminAmount, tvAdminReason;
        ImageView ivAdminColor;


        public AdminExpenditureViewHolder(View itemView) {
            super(itemView);
            tvAdminDatetime = itemView.findViewById(R.id.tvAdminDateTime);
            tvAdminAmount = itemView.findViewById(R.id.tvAdminAmount);
            tvAdminReason = itemView.findViewById(R.id.tvAdminReason);
            ivAdminColor = itemView.findViewById(R.id.ivAdminColor);

        }

        void bindView(final Expenditure expenditure) {
            tvAdminDatetime.setText(expenditure.getDatetime());
            tvAdminAmount.setText(expenditure.getAmount());
            tvAdminReason.setText(expenditure.getReason());
            if (expenditure.getReason().equals("Income")) {
                ivAdminColor.setBackgroundColor(Color.GREEN);
            } else {
                ivAdminColor.setBackgroundColor(Color.RED);
            }

        }
    }
}
