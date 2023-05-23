package com.example.carlobby;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class AllCarsActivity extends AppCompatActivity {

    private TextView allcarsback;
    private Button buttonAddCar, gotomenu;
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

        // button to go to all menu
        gotomenu = findViewById(R.id.gotomenu);
        gotomenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllCarsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //go back to menu
        TextView myTextView = findViewById(R.id.allcarsback);
        myTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllCarsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        // Initialize database handler
        db = new DatabaseHandler(this);

        // Fetch the list of cars from the database
        ArrayList<Car> cars = db.getAllCars();

        // Initialize RecyclerView
        carListRecyclerView = findViewById(R.id.carListRecyclerView);
        carListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set the adapter for the RecyclerView
        CarAdapter carAdapter = new CarAdapter(cars, this);
        carListRecyclerView.setAdapter(carAdapter);
    }
}
