package com.example.foodapp.adaptar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.detailsActivity;
import com.example.foodapp.databinding.MenuItemBinding;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private final List<String> menuItemsName;
    private final List<String> menuItemPrice;
    private final List<Integer> menuImages;
    private final Context context;
    private OnItemClickListener itemClickListener;

    public MenuAdapter(List<String> menuItemsName, List<String> menuItemPrice, List<Integer> menuImages, Context context) {
        this.menuItemsName = menuItemsName;
        this.menuItemPrice = menuItemPrice;
        this.menuImages = menuImages;
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MenuItemBinding binding = MenuItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MenuViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return menuItemsName.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        private final MenuItemBinding binding;

        public MenuViewHolder(@NonNull MenuItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(position);
                    }
                    // Navigate to DetailsActivity
                    Intent intent = new Intent(context, detailsActivity.class);
                    intent.putExtra("MenuItemName", menuItemsName.get(position));
                    intent.putExtra("MenuItemImage", menuImages.get(position));
                    context.startActivity(intent);
                }
            });
        }

        public void bind(int position) {
            binding.menuFoodName.setText(menuItemsName.get(position));
            binding.menuPrice.setText(menuItemPrice.get(position));
            binding.menuImage.setImageResource(menuImages.get(position));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
