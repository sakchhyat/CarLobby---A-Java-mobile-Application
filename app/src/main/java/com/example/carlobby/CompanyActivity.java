package com.example.carlobby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CompanyActivity extends AppCompatActivity {

    private TextView nameTextView, addressTextView, profitTextView, soldTextView, textViewPhone, textViewEmail;
    private Button buttonModifyCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        // Initialize the views
        nameTextView = findViewById(R.id.textViewCompanyName);
        addressTextView = findViewById(R.id.textViewCompanyAddress);
        profitTextView = findViewById(R.id.textViewProfit);
        soldTextView = findViewById(R.id.textViewCarsSold);
        buttonModifyCompany = findViewById(R.id.button_modify_company);
        textViewPhone = findViewById(R.id.textViewPhone);
        textViewEmail = findViewById(R.id.textViewEmail);

        // Load the company data from the database
        DatabaseHandler db = new DatabaseHandler(this);

        Company newCompany = new Company(1, "The Car Lobby Pvt. Ltd.", "546 Baker Street", "519-434-3280", "info@tcl.com");
        db.addCompany(newCompany);

        Company companyOld = db.getCompany(1);


        // Set the name and address views
        nameTextView.setText(companyOld.getName());
        addressTextView.setText(companyOld.getAddress());

        // Load the number of cars sold and profit from the database
        int carsSold = db.getNumberOfCarsSold();
        double profit = db.getProfitFromCarSales();

        // Set the sold and profit views
        soldTextView.setText("" + carsSold);
        profitTextView.setText("$" + String.format("%.2f", profit));

        //set phone and email
        textViewPhone.setText(companyOld.getPhone());
        textViewEmail.setText(companyOld.getEmail());

        buttonModifyCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ModifyCompanyActivity.class);
                startActivity(intent);
            }
        });

        //go back to menu
        TextView myTextView = findViewById(R.id.allcarsback);
        myTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompanyActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}