package com.example.naman.namanapp.activities.DriverFiles;

import android.content.Context;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PassbookActivity extends AppCompatActivity {

    ArrayList<Expenditure> expenditures = new ArrayList<>();
    TextView tvName, tvAmount;
    RecyclerView rvExpenditure;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passbook);

        tvAmount = findViewById(R.id.tvPassAmt);
        tvName = findViewById(R.id.tvPassName);

        tvName.setText(FirebaseDatabase.getInstance().getReference().child("Users").child(auth.getCurrentUser().getUid()).child("name").getKey());
        tvAmount.setText(FirebaseDatabase.getInstance().getReference().child("Users").child(auth.getCurrentUser().getUid()).child("amount").getKey());

        auth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child("Expenditure");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Expenditure expenditure = postSnapshot.getValue(Expenditure.class);
                    expenditures.add(expenditure);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        rvExpenditure = findViewById(R.id.rvExpenditure);
        rvExpenditure.setLayoutManager(new LinearLayoutManager(this));

        final PassbookAdapter passbookAdapter = new PassbookAdapter(this, expenditures);

        rvExpenditure.setAdapter(passbookAdapter);


    }
}

class PassbookAdapter extends RecyclerView.Adapter<PassbookAdapter.ExpenditureViewHolder> {

    Context context;
    ArrayList<Expenditure> expenditures;

    public PassbookAdapter(Context context, ArrayList<Expenditure> expenditures) {
        this.context = context;
        this.expenditures = expenditures;
    }

    @Override
    public ExpenditureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.list_item_expenditure, parent, false);

        return new ExpenditureViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ExpenditureViewHolder holder, int position) {
        holder.bindView(expenditures.get(position));
    }

    @Override
    public int getItemCount() {
        return expenditures.size();
    }

    class ExpenditureViewHolder extends RecyclerView.ViewHolder {

        TextView tvDatetime, tvAmount, tvReason;
        ImageView ivColor;


        public ExpenditureViewHolder(View itemView) {
            super(itemView);
            tvDatetime = itemView.findViewById(R.id.tvDateTime);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvReason = itemView.findViewById(R.id.tvReason);
            ivColor = itemView.findViewById(R.id.ivColor);

        }

        void bindView(final Expenditure expenditure) {
            tvDatetime.setText(expenditure.getDatetime());
            tvAmount.setText(expenditure.getAmount());
            tvReason.setText(expenditure.getReason());
            if (expenditure.getReason().equals("Income")) {
                ivColor.setBackgroundColor(Color.GREEN);
            } else {
                ivColor.setBackgroundColor(Color.RED);
            }

        }
    }
}
