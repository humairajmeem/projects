package com.example.foodapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.foodapp.R;
import com.example.foodapp.adaptar.PopularAdapter;
import com.example.foodapp.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        // Set click listener for the menu button
        binding.ViewAllMenu.setOnClickListener(v -> {
            // Initialize and show the bottom sheet dialog
            MenuBottomSheetFragment bottomSheetDialog = new MenuBottomSheetFragment();
            bottomSheetDialog.show(getParentFragmentManager(), "MenuBottomSheet");
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize Image Slider
        ArrayList<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.banner1, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.banner2, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.banner3, ScaleTypes.FIT));

        binding.imageSlider.setImageList(imageList);

        binding.imageSlider.setItemClickListener(new ItemClickListener() {
            @Override
            public void doubleClick(int position) {
                Toast.makeText(requireContext(), "Image " + position + " double-clicked!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemSelected(int position) {
                Toast.makeText(requireContext(), "Selected Image " + position, Toast.LENGTH_SHORT).show();
            }
        });

        // Initialize RecyclerView with PopularAdapter
        List<String> foodNames = Arrays.asList("Cookies", "Salad", "Ice Cream", "Soup", "Pasta", "Shawarma");
        List<String> prices = Arrays.asList("$5", "$7", "$8", "$10", "$10", "$5");
        List<Integer> popularFoodImages = Arrays.asList(
                R.drawable.menu1,
                R.drawable.menu2,
                R.drawable.menu3,
                R.drawable.menu4,
                R.drawable.menu5,
                R.drawable.menu6
        );

        PopularAdapter adapter = new PopularAdapter(foodNames, prices, popularFoodImages, requireContext());
        binding.populerRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.populerRecyclerView.setAdapter(adapter);

        binding.populerRecyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
