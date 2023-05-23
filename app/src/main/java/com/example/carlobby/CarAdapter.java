package com.example.carlobby;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private ArrayList<Car> carList;
    private Context context;
    private boolean showModifyButton;

    public CarAdapter(ArrayList<Car> carList, Context context) {
        this.carList = carList;
        this.context = context;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = carList.get(position);
        holder.carMakeAndModel.setText(car.getMake() + " " + car.getModel());

        if (car.getIsSold()){
            holder.btnModify.setVisibility(View.GONE);
        }

        if (context instanceof SoldCarsActivity) {
            holder.btnModify.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return carList.size();
    }

    public class CarViewHolder extends RecyclerView.ViewHolder {

        TextView carMakeAndModel;
        Button btnModify, btnViewDetails;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);

            carMakeAndModel = itemView.findViewById(R.id.carMakeAndModel);
            btnViewDetails = itemView.findViewById(R.id.view_details_button);
            btnModify = itemView.findViewById(R.id.modify_button);

            btnModify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int carId = carList.get(getAdapterPosition()).getId();

                    Intent intent = new Intent(context, UpdateCarActivity.class);
                    intent.putExtra("carId", carId);
                    context.startActivity(intent);
                }
            });

            btnViewDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int carId = carList.get(getAdapterPosition()).getId();

                    Intent intent = new Intent(context, ViewCarDetailsActivity.class);
                    intent.putExtra("carId", carId);
                    context.startActivity(intent);
                }
            });
        }
    }
}
