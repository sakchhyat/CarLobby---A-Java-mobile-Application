package com.example.carlobby;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private CardView allCarsCardView;
    private CardView availableCarsCardView;
    private CardView soldCarsCardView;
    private CardView viewComanpy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the CardView
        allCarsCardView = findViewById(R.id.allcars);
        availableCarsCardView = findViewById(R.id.availableCars);
        soldCarsCardView = findViewById(R.id.soldCars);
       viewComanpy = findViewById(R.id.companyCard);

        // Set a click listener on the CardView

        viewComanpy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,CompanyActivity.class);
                startActivity(i);
            }
        });

        availableCarsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AvailableCarsActivity.class);
                startActivity(intent);
            }
        });

        soldCarsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SoldCarsActivity.class);
                startActivity(intent);
            }
        });


        allCarsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AllCarsActivity.class);
                startActivity(intent);
            }
        });
    }
}

