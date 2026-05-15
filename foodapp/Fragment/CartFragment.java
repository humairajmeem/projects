package com.example.foodapp.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodapp.R;
import com.example.foodapp.adaptar.CartAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CartFragment extends Fragment {

    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        // Initialize the RecyclerView
        cartRecyclerView = view.findViewById(R.id.cartRecyclerView);

        // Set up data
        List<String> cartFoodNames = Arrays.asList("Burger", "Sandwich", "Momo", "Item", "Sandwich", "Momo");
        List<String> cartItemPrices = Arrays.asList("$5", "$6", "$8", "$9", "$10", "$10");
        List<Integer> cartImages = Arrays.asList(
                R.drawable.menu1,
                R.drawable.menu2,
                R.drawable.menu3,
                R.drawable.menu4,
                R.drawable.menu5,
                R.drawable.menu6
        );

        // Initialize adapter
        cartAdapter = new CartAdapter(
                new ArrayList<>(cartFoodNames),
                new ArrayList<>(cartItemPrices),
                new ArrayList<>(cartImages)
        );

        // Set up RecyclerView with the adapter
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        cartRecyclerView.setAdapter(cartAdapter);

        return view; // Return the fragment's view
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Nullify references to avoid memory leaks
        cartRecyclerView = null;
        cartAdapter = null;
    }
}
