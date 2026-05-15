package com.example.foodapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.foodapp.R;
import com.example.foodapp.adaptar.NotificationAdapter;
import com.example.foodapp.databinding.FragmentNotificationBottomBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Arrays;

public class NotificationBottomFragment extends BottomSheetDialogFragment {

    private FragmentNotificationBottomBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNotificationBottomBinding.inflate(inflater, container, false);

        // Notifications list
        ArrayList<String> notifications = new ArrayList<>(Arrays.asList(
                "Your order has been cancelled successfully",
                "Order has been taken by the Rider",
                "Congratulations! Your order has been placed"
        ));

        // Notification images list
        ArrayList<Integer> notificationImages = new ArrayList<>(Arrays.asList(
                R.drawable.sademoji,
                R.drawable.truck,
                R.drawable.congratulation
        ));

        // Setting up the adapter
        NotificationAdapter adapter = new NotificationAdapter(notifications, notificationImages);
        binding.notificationRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.notificationRecyclerView.setAdapter(adapter);

        return binding.getRoot();
    }

    public static NotificationBottomFragment newInstance() {
        return new NotificationBottomFragment();
    }
}
