package com.example.carlobby;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddCarActivity extends AppCompatActivity {

    // Database handler
    private DatabaseHandler db;

    // UI elements
    private EditText etMake, etModel, etCondition, etCylinders, etYear, etDoors, etPrice, etColor;
    private Button btnAddCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        // Initialize database handler
        db = new DatabaseHandler(this);

        // Initialize UI elements
        etMake = findViewById(R.id.make);
        etModel = findViewById(R.id.model);
        etCondition = findViewById(R.id.condition);
        etCylinders = findViewById(R.id.engine_cylinders);
        etYear = findViewById(R.id.year);
        etDoors = findViewById(R.id.num_doors);
        etPrice = findViewById(R.id.price);
        etColor = findViewById(R.id.color);
        btnAddCar = findViewById(R.id.add_car_button);

        //go back to menu
        TextView myTextView = findViewById(R.id.allcarsback);
        myTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCarActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Add click listener to the "Add Car" button
        btnAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the car details from the input fields
                String make = etMake.getText().toString();
                String model = etModel.getText().toString();
                String condition = etCondition.getText().toString();
                String cylinders = etCylinders.getText().toString();
                String yearStr = etYear.getText().toString();
                String doorsStr = etDoors.getText().toString();
                String priceStr = etPrice.getText().toString();
                String color = etColor.getText().toString();

                // Check if required fields are filled in
                if (make.isEmpty() || model.isEmpty() || condition.isEmpty() || cylinders.isEmpty()
                        || yearStr.isEmpty() || doorsStr.isEmpty() || priceStr.isEmpty() || color.isEmpty()) {
                    Toast.makeText(AddCarActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Parse the numeric fields
                int year = Integer.parseInt(yearStr);
                int doors = Integer.parseInt(doorsStr);
                double price = Double.parseDouble(priceStr);

                // Create a new car object with the details
                Car car = new Car(0, make, model, condition, cylinders, year, doors, price, color, false);

                // Add the car to the database
                db.addCar(car);

                // Display a toast message to indicate success
                Toast.makeText(AddCarActivity.this, "Car added successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddCarActivity.this,AllCarsActivity.class);
                startActivity(intent);

            }
        });

    }
}

