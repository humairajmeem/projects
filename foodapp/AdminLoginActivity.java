package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminLoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private DatabaseReference database;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize view binding
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize Firebase Auth and Database Reference
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        // Login button click listener
        binding.loginbutton.setOnClickListener(view -> {
            String email = binding.email.getText().toString().trim();
            String password = binding.password.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            } else {
                loginAdmin(email, password);
            }
        });

        // Redirect to sign-up activity
        binding.donthavebutton.setOnClickListener(view -> {
            startActivity(new Intent(AdminLoginActivity.this, AdminsignActivity.class));
        });
    }

    private void loginAdmin(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                String adminId = auth.getCurrentUser().getUid();

                // Verify if the logged-in user is an admin
                database.child("admins").child(adminId).get().addOnCompleteListener(dataTask -> {
                    if (dataTask.isSuccessful() && dataTask.getResult().exists()) {
                        Toast.makeText(this, "Admin login successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AdminLoginActivity.this, AdminDashboardActivity.class));
                        finish();
                    } else {
                        Toast.makeText(this, "Access denied: Not an admin", Toast.LENGTH_SHORT).show();
                        auth.signOut(); // Sign out if the user is not an admin
                    }
                });
            } else {
                Toast.makeText(this, "Login failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
