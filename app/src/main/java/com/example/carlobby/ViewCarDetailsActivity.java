package com.example.carlobby;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewCarDetailsActivity extends AppCompatActivity {

    private TextView carMakeModelTextView, carYearTextView, carColorTextView, carPriceTextView, car_condition, car_engine, car_doors;
    private Button editCarButton;
    private int carId;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_car);

        // Get the car id from the intent
        carId = getIntent().getIntExtra("carId", 0);

        // Initialize database handler
        db = new DatabaseHandler(this);

        // Initialize views
        carMakeModelTextView = findViewById(R.id.car_make_model_textview);
        carYearTextView = findViewById(R.id.car_year_textview);
        carColorTextView = findViewById(R.id.car_color_textview);
        carPriceTextView = findViewById(R.id.car_price_textview);
        editCarButton = findViewById(R.id.edit_car_button);
        car_condition = findViewById(R.id.car_condition);
        car_doors = findViewById(R.id.car_doors);
        car_engine = findViewById(R.id.car_engine);

        // Get the car from the database
        Car car = db.getCar(carId);

        // Set the car details to the views
        carMakeModelTextView.setText(car.getMake() + " " + car.getModel());
        carYearTextView.setText(String.valueOf(car.getYear()));
        carColorTextView.setText(car.getColor());
        carPriceTextView.setText(String.valueOf(car.getPrice()));
        car_condition.setText(String.valueOf(car.getCondition()));
        car_engine.setText(String.valueOf(car.getCylinders()));
        car_doors.setText(String.valueOf(car.getDoors()));

        //go back to menu
        TextView myTextView = findViewById(R.id.allcarsback);
        myTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewCarDetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for edit button
        editCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UpdateCarActivity.class);
                intent.putExtra("carId", carId);
                startActivity(intent);
            }
        });
    }
}


