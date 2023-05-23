package com.example.carlobby;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ModifyCompanyActivity extends AppCompatActivity {

    EditText editTextCompanyName, editTextCompanyAddress, editTextCompanyPhone, editTextCompanyEmail;
    Button buttonUpdate;

    private int companyId;

    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_company);

        editTextCompanyName = findViewById(R.id.editTextName);
        editTextCompanyAddress = findViewById(R.id.editTextAddress);
        editTextCompanyPhone = findViewById(R.id.editTextPhone);
        editTextCompanyEmail = findViewById(R.id.editTextEmail);
        buttonUpdate = findViewById(R.id.buttonUpdate);

        db = new DatabaseHandler(this);
        Company company = db.getCompany(1);

        // Set the company data to the UI fields
        editTextCompanyName.setText(company.getName());
        editTextCompanyAddress.setText(company.getAddress());
        editTextCompanyPhone.setText(company.getPhone());
        editTextCompanyEmail.setText(company.getEmail());

        // Set up the update button click listener
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the new values from the UI fields
                String name = editTextCompanyName.getText().toString().trim();
                String address = editTextCompanyAddress.getText().toString().trim();
                String phone = editTextCompanyPhone.getText().toString().trim();
                String email = editTextCompanyEmail.getText().toString().trim();

                // Validate the input fields
                if (name.isEmpty()) {
                    editTextCompanyName.setError("Name is required");
                    editTextCompanyName.requestFocus();
                    return;
                }

                if (address.isEmpty()) {
                    editTextCompanyAddress.setError("Address is required");
                    editTextCompanyAddress.requestFocus();
                    return;
                }

                if (phone.isEmpty()) {
                    editTextCompanyPhone.setError("Phone number is required");
                    editTextCompanyPhone.requestFocus();
                    return;
                }

                if (email.isEmpty()) {
                    editTextCompanyEmail.setError("Email is required");
                    editTextCompanyEmail.requestFocus();
                    return;
                }

                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editTextCompanyEmail.setError("Enter a valid email address");
                    editTextCompanyEmail.requestFocus();
                    return;
                }

                // Update the company object with the new values
                Company updatedCompany = new Company(1, name, address, phone, email);

                // Update the company in the database
                int rowsAffected = db.updateCompany(updatedCompany);

                if (rowsAffected > 0) {
                    // Success message
                    Toast.makeText(ModifyCompanyActivity.this, "Company updated successfully!", Toast.LENGTH_SHORT).show();

                    // Navigate back to the CompanyActivity
                    Intent intent = new Intent(getApplicationContext(), CompanyActivity.class);
                    startActivity(intent);
                } else {
                    // Error message
                    Toast.makeText(ModifyCompanyActivity.this, "Failed to update company.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}



