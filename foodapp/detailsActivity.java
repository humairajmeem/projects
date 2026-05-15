package com.example.foodapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.databinding.ActivityDetailsBinding;

public class detailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout using ViewBinding
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Retrieve data passed from the previous activity
        String foodName = getIntent().getStringExtra("MenuItemName");
        int foodImage = getIntent().getIntExtra("MenuItemImage", 0);

        // Set the data to views
        if (foodName != null) {
            binding.detailFoodName.setText(foodName);
        }
        binding.DetailFoodImage.setImageResource(foodImage);

        // Set the click listener for the back button
        binding.imageButton.setOnClickListener(v -> finish());
    }
}
