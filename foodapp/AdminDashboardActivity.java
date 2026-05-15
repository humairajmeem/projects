package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.databinding.ActivityAdminDashboardBinding;

public class AdminDashboardActivity extends AppCompatActivity {
    private ActivityAdminDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout using ViewBinding
        binding = ActivityAdminDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set a click listener for the "addmenu" button
        binding.addmenu.setOnClickListener(view -> {
            Intent intent = new Intent(AdminDashboardActivity.this, AddItemActivity.class);
            startActivity(intent);
        });

        // Set a click listener for the "allItemMenu" button
        binding.allItemMenu.setOnClickListener(view -> {
            Intent intent = new Intent(AdminDashboardActivity.this, AllItemActivity.class);
            startActivity(intent);
        });
    }
}
