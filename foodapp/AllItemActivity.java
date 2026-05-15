package com.example.foodapp;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.foodapp.adaptar.AddItemAdapter;
import com.example.foodapp.databinding.ActivityAllItemBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class AllItemActivity extends AppCompatActivity {

    private ActivityAllItemBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the binding
        binding = ActivityAllItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Data for the RecyclerView
        ArrayList<String> menuFoodName = new ArrayList<>(Arrays.asList("Burger", "Sandwich", "Momo", "Item", "Sandwich", "Momo"));
        ArrayList<String> menuItemPrice = new ArrayList<>(Arrays.asList("350tk", "250tk", "300tk", "550tk", "250tk", "350tk"));
        ArrayList<Integer> menuImage = new ArrayList<>(Arrays.asList(
                R.drawable.menu1_admin,
                R.drawable.menu2_admin,
                R.drawable.menu3_admin,
                R.drawable.menu4_admin,
                R.drawable.menu5_admin,
                R.drawable.menu1_admin
        ));

        // Back button functionality
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Set up the adapter and RecyclerView
        AddItemAdapter adapter = new AddItemAdapter(menuFoodName, menuItemPrice, menuImage);
        binding.MenuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.MenuRecyclerView.setAdapter(adapter);
    }
}
