package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.databinding.ActivitySignBinding;
import com.example.foodapp.model.AdminUserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminsignActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private DatabaseReference database;
    private ActivitySignBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize view binding
        binding = ActivitySignBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize Firebase Auth and Database Reference
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        // Set click listener for sign-up button
        binding.createUserButton.setOnClickListener(view -> {
            String name = binding.name.getText().toString().trim();
            String email = binding.emailOrPhone.getText().toString().trim();
            String password = binding.password.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            } else {
                createAdminAccount(name, email, password);
            }
        });

        // Redirect to login activity
        binding.alreadyhaveaccount.setOnClickListener(view -> {
            startActivity(new Intent(AdminsignActivity.this, AdminLoginActivity.class));
        });
    }

    private void createAdminAccount(String name, String email, String password) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                String adminId = auth.getCurrentUser().getUid();
                AdminUserModel admin = new AdminUserModel(adminId, name, email, "admin");

                // Save admin data to the database
                database.child("admins").child(adminId).setValue(admin).addOnCompleteListener(saveTask -> {
                    if (saveTask.isSuccessful()) {
                        Toast.makeText(this, "Admin account created successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AdminsignActivity.this, AdminLoginActivity.class));
                        finish();
                    } else {
                        Toast.makeText(this, "Failed to save admin data", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, "Account creation failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
