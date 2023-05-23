package com.example.carlobby;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class AvailableCarsActivity extends AppCompatActivity {

    private Button buttonAddCar;
    private RecyclerView carListRecyclerView;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_all_cars);

        buttonAddCar = findViewById(R.id.addCarButton);
        buttonAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddCarActivity.class);
                startActivity(intent);
            }
        });

        // Initialize database handler
        db = new DatabaseHandler(this);

        // Fetch the list of cars from the database
        ArrayList<Car> cars = db.getAvailableCars();

        // Initialize RecyclerView
        carListRecyclerView = findViewById(R.id.carListRecyclerView);
        carListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set the adapter for the RecyclerView
        CarAdapter carAdapter = new CarAdapter(cars, this);
        carListRecyclerView.setAdapter(carAdapter);
    }
}
