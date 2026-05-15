package com.example.foodapp.adaptar;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.databinding.CartItemBinding;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private final List<String> cartItems;
    private final List<String> cartItemPrices;
    private final List<Integer> cartImages;
    private final int[] itemQuantities;

    public CartAdapter(List<String> cartItems, List<String> cartItemPrices, List<Integer> cartImages) {
        this.cartItems = cartItems;
        this.cartItemPrices = cartItemPrices;
        this.cartImages = cartImages;
        this.itemQuantities = new int[cartItems.size()];
        for (int i = 0; i < cartItems.size(); i++) {
            itemQuantities[i] = 1; // Default quantity set to 1
        }
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CartItemBinding binding = CartItemBinding.inflate(inflater, parent, false);
        return new CartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        private final CartItemBinding binding;

        public CartViewHolder(CartItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(int position) {
            int quantity = itemQuantities[position];
            binding.cartFoodName.setText(cartItems.get(position));
            binding.CartItemPrice.setText(cartItemPrices.get(position));
            binding.cartImage.setImageResource(cartImages.get(position));
            binding.cartitemquantity.setText(String.valueOf(quantity));

            // Set up button listeners
            binding.minusbutton.setOnClickListener(v -> decreaseQuantity(getAdapterPosition()));
            binding.plusbutton.setOnClickListener(v -> increaseQuantity(getAdapterPosition()));
            binding.deleteButton.setOnClickListener(v -> deleteItem(getAdapterPosition()));
        }
    }

    private void increaseQuantity(int position) {
        if (position != RecyclerView.NO_POSITION && itemQuantities[position] < 10) {
            itemQuantities[position]++;
            notifyItemChanged(position);
        }
    }

    private void decreaseQuantity(int position) {
        if (position != RecyclerView.NO_POSITION && itemQuantities[position] > 1) {
            itemQuantities[position]--;
            notifyItemChanged(position);
        }
    }

    private void deleteItem(int position) {
        if (position != RecyclerView.NO_POSITION) {
            cartItems.remove(position);
            cartImages.remove(position);
            cartItemPrices.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, cartItems.size());
        }
    }
}
