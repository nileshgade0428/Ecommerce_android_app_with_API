package com.example.insidethematrix;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.insidethematrix.Model.ProductModel;
import com.google.android.gms.analytics.ecommerce.Product;

import java.util.List;

public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.ViewHolder> {

    private List<Product> cartItemsList;

    public CartItemsAdapter(List<Product> cartItemsList) {
        this.cartItemsList = cartItemsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product cartItem = cartItemsList.get(position);
        holder.textProductName.setText(cartItem.getProductName());
        holder.textCount.setText("Quantity: " + cartItem.getQuantity());
        holder.textPrice.setText("Price: ₹" + cartItem.getPrice());
    }

    @Override
    public int getItemCount() {
        return cartItemsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textProductName;
        TextView textCount;
        TextView textPrice;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textProductName = itemView.findViewById(R.id.textProductName);
            textCount = itemView.findViewById(R.id.textCount);
            textPrice = itemView.findViewById(R.id.textPrice);
        }
    }
}
