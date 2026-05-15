package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foodapp.databinding.ActivityForAllUserBinding;

public class activity_for_all_userActivity extends AppCompatActivity {

    private ActivityForAllUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize binding
        binding = ActivityForAllUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Handle button clicks
        binding.userButton.setOnClickListener(v -> {
            Intent intent = new Intent(activity_for_all_userActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        binding.adminButton.setOnClickListener(v -> {
            Intent intent = new Intent(activity_for_all_userActivity.this, AdminLoginActivity.class);
            startActivity(intent);
        });

        binding.restaurantButton.setOnClickListener(v -> {
            Intent intent = new Intent(activity_for_all_userActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        // Apply window insets for edge-to-edge layout
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
