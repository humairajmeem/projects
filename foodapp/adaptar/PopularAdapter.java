package com.example.foodapp.adaptar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.detailsActivity;
import com.example.foodapp.databinding.PopularItemBinding;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder> {

    private final List<String> items;
    private final List<String> prices;
    private final List<Integer> images;
    private final Context context;

    public PopularAdapter(List<String> items, List<String> prices, List<Integer> images, Context context) {
        this.items = items;
        this.prices = prices;
        this.images = images;
        this.context = context;
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PopularItemBinding binding = PopularItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PopularViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        holder.bind(items.get(position), prices.get(position), images.get(position));

        // Set onClickListener for navigating to the DetailsActivity
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, detailsActivity.class);
            intent.putExtra("MenuItemName", items.get(position));
            intent.putExtra("MenuItemImage", images.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class PopularViewHolder extends RecyclerView.ViewHolder {

        private final PopularItemBinding binding;

        public PopularViewHolder(@NonNull PopularItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(String item, String price, int image) {
            binding.FoodNamePopular.setText(item);
            binding.PricePopular.setText(price);
            binding.imageView7.setImageResource(image);
        }
    }
}
