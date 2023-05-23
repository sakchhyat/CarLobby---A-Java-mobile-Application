package com.example.carlobby;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateCarActivity extends AppCompatActivity {
    private EditText editTextPrice;
    private EditText editTextDateSold;
    private Button buttonSave;

    private DatabaseHandler db;
    private Car car;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_car);

        db = new DatabaseHandler(this);
        int carId = getIntent().getIntExtra("carId", -1);
        car = db.getCar(carId);



        editTextPrice = findViewById(R.id.editTextPrice);
        editTextDateSold = findViewById(R.id.editTextDateSold);
        buttonSave = findViewById(R.id.buttonUpdate);

        // Populate the UI fields with the car details
        editTextPrice.setText(String.valueOf(car.getPrice()));
        editTextDateSold.setText(String.valueOf(car.getYear()));

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the new values from the UI fields
                String priceString = editTextPrice.getText().toString();
                String dateSold = editTextDateSold.getText().toString();

                // Validate the price field
                if (TextUtils.isEmpty(priceString)) {
                    editTextPrice.setError("Price is required");
                    editTextPrice.requestFocus();
                    return;
                }

                double price = Double.parseDouble(priceString);

                // Validate the date sold field
                if (TextUtils.isEmpty(dateSold)) {
                    editTextDateSold.setError("Date sold is required");
                    editTextDateSold.requestFocus();
                    return;
                }

                int year = Integer.parseInt(dateSold);

                // Update the car object with the new values
                car.setPrice(price);
                car.setYear(year);

                // Save the updated car to the database
                db.updateCar(car,true);

                // Go back to the AvailableCarsActivity
                Intent intent = new Intent(getApplicationContext(), AllCarsActivity.class);
                startActivity(intent);
            }
        });

        //go back to menu
        TextView myTextView = findViewById(R.id.allcarsback);
        myTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateCarActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}

