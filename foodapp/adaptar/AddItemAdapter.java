package com.example.foodapp.adaptar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.databinding.ItemItemBinding;

import java.util.ArrayList;

public class AddItemAdapter extends RecyclerView.Adapter<AddItemAdapter.AddItemViewHolder> {

    private ArrayList<String> menuItemName;
    private ArrayList<String> menuItemPrice;
    private ArrayList<Integer> menuItemImage;
    private ArrayList<Integer> itemQuantities;

    public AddItemAdapter(ArrayList<String> menuItemName, ArrayList<String> menuItemPrice, ArrayList<Integer> menuItemImage) {
        this.menuItemName = menuItemName;
        this.menuItemPrice = menuItemPrice;
        this.menuItemImage = menuItemImage;

        // Initialize item quantities to 1 for all items
        this.itemQuantities = new ArrayList<>();
        for (int i = 0; i < menuItemName.size(); i++) {
            this.itemQuantities.add(1);
        }
    }

    @NonNull
    @Override
    public AddItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemItemBinding binding = ItemItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AddItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AddItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return menuItemName.size();
    }

    public class AddItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemItemBinding binding;

        public AddItemViewHolder(ItemItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(int position) {
            // Set item data
            binding.foodNameTextView.setText(menuItemName.get(position));
            binding.priceTextView.setText(menuItemPrice.get(position));
            binding.foodImageView.setImageResource(menuItemImage.get(position));
            binding.quantityTextView.setText(String.valueOf(itemQuantities.get(position)));

            // Set click listeners
            binding.minusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    decreaseQuantity(position);
                }
            });

            binding.pluseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    increaseQuantity(position);
                }
            });

            binding.deleteButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteItem(position);
                }
            });
        }
    }

    private void increaseQuantity(int position) {
        if (itemQuantities.get(position) < 10) {
            itemQuantities.set(position, itemQuantities.get(position) + 1);
            notifyItemChanged(position);
        }
    }

    private void decreaseQuantity(int position) {
        if (itemQuantities.get(position) > 1) {
            itemQuantities.set(position, itemQuantities.get(position) - 1);
            notifyItemChanged(position);
        }
    }

    private void deleteItem(int position) {
        menuItemName.remove(position);
        menuItemPrice.remove(position);
        menuItemImage.remove(position);
        itemQuantities.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, menuItemName.size());
    }
}

