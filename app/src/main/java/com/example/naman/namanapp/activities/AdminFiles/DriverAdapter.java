package com.example.naman.namanapp.activities.AdminFiles;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.naman.namanapp.R;

import java.util.ArrayList;

/**
 * Created by Neera on 01/02/19.
 */

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.DriverViewHolder> {

    ArrayList<Driver> drivers = new ArrayList<>();
    Context context;

    public DriverAdapter(Context context) {
        this.context = context;
    }

    public void updatedrivers(ArrayList<Driver> photos) {
        this.drivers = photos;
        notifyDataSetChanged();
    }

    @Override
    public DriverViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new DriverAdapter.DriverViewHolder(li.inflate(R.layout.list_item_driver, parent, false));
    }

    @Override
    public void onBindViewHolder(DriverViewHolder holder, int position) {
        holder.bindView(drivers.get(position));
    }

    @Override
    public int getItemCount() {
        return drivers.size();
    }

    class DriverViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImg;
        TextView tvName, tvCarNo, tvCarName;
        LinearLayout llDrivers;

        public DriverViewHolder(View itemView) {
            super(itemView);
            ivImg = itemView.findViewById(R.id.ivImg);
            tvCarName = itemView.findViewById(R.id.tvCarName);
            tvCarNo = itemView.findViewById(R.id.tvCarNo);
            tvName = itemView.findViewById(R.id.tvName);
            llDrivers = itemView.findViewById(R.id.llDrivers);
        }

        void bindView(final Driver driver) {

            tvName.setText(driver.getName());
            tvCarNo.setText(driver.getCarNo());
            tvCarName.setText(driver.getCarName());

            llDrivers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, AdminDriverActivity.class);
                    context.startActivity(i);
                }
            });

            // TODO: add for photos

        }
    }

}
