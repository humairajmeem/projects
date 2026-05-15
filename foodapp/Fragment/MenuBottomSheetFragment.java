package com.example.foodapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.foodapp.R;
import com.example.foodapp.adaptar.MenuAdapter;
import com.example.foodapp.databinding.FragmentMenuBottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuBottomSheetFragment extends BottomSheetDialogFragment {

    private FragmentMenuBottomSheetBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Initialize the binding
        binding = FragmentMenuBottomSheetBinding.inflate(inflater, container, false);

        // Set up the back button click listener
        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        // Sample menu data
        List<String> menuFoodNames = Arrays.asList("Cookies", "Salad", "Ice-Cream", "Soup","Pasta","Swarma", "Pizza", "Burger", "California Roll", "Spicy Chicken","Pizza","Spicy Chicken");
        List<String> menuItemPrices = Arrays.asList("$5", "$6", "$8", "$9", "$10", "$10", "$5", "$6", "$8", "$9", "$10", "$10");
        List<Integer> menuImages = Arrays.asList(
                R.drawable.menu1,
                R.drawable.menu2,
                R.drawable.menu3,
                R.drawable.menu4,
                R.drawable.menu5,
                R.drawable.menu6,
                R.drawable.menu7,
                R.drawable.menu8,
                R.drawable.menu9,
                R.drawable.menu10,
                R.drawable.menu7,
                R.drawable.menu10
        );

        // Initialize the adapter with data
        MenuAdapter adapter = new MenuAdapter(
                new ArrayList<>(menuFoodNames),
                new ArrayList<>(menuItemPrices),
                new ArrayList<>(menuImages),requireContext()
        );

        // Set up RecyclerView with the adapter
        binding.menuRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.menuRecyclerView.setAdapter(adapter);

        // Return the root view
        return binding.getRoot();
    }
}
