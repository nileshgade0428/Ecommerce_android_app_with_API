package com.example.insidethematrix;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.insidethematrix.Model.ProductModel;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductsActivity extends AppCompatActivity {

    private ArrayList<ProductModel> arrayList;
    private TextView cartCountTextView;
    private RecyclerView recyclerViewProducts;
    private HashMap<String, Integer> cartItemsMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_item);

        arrayList = new ArrayList<>();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart, menu);

        MenuItem cartItem = menu.findItem(R.id.action_cart);
        View cartActionView = cartItem.getActionView();
        cartCountTextView = cartActionView.findViewById(R.id.cart_count);

        cartActionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCartItems();
            }
        });

        updateCartItemCount();

        return true;
    }

    public void addToCart(ProductModel arraylist) {
        String productName = arrayList.getTitle();

        if (cartItemsMap.containsKey(productName)) {
            int itemCount = cartItemsMap.get(productName);
            cartItemsMap.put(productName, itemCount + 1);
        } else {
            cartItemsMap.put(productName, 1);
        }

        updateCartItemCount();
        Toast.makeText(this, "Item added to cart", Toast.LENGTH_SHORT).show();
    }

    private void updateCartItemCount() {
        int totalCartItemCount = 0;

        for (int itemCount : cartItemsMap.values()) {
            totalCartItemCount += itemCount;
        }

        if (cartCountTextView != null) {
            if (totalCartItemCount > 0) {
                cartCountTextView.setVisibility(View.VISIBLE);
                cartCountTextView.setText(String.valueOf(totalCartItemCount));
            } else {
                cartCountTextView.setVisibility(View.INVISIBLE);
            }
        }
    }

    // ... (inside ProductsActivity)
    private void showCartItems() {
        StringBuilder cartItemsText = new StringBuilder();
        double totalAmount = 0.0;

        for (String productName : cartItemsMap.keySet()) {
            int itemCount = cartItemsMap.get(productName);
            ProductModel product = getProductByName(productName);
            double itemTotal = product.getPrice() * itemCount;
            totalAmount += itemTotal;

            cartItemsText.append(itemCount).append("x ").append(product.getTitle())
                    .append(" ($").append(String.format("%.2f", itemTotal)).append(")\n");
        }

        String totalAmountFormatted = String.format("%.2f", totalAmount);
        String cartItemsDetails = cartItemsText.toString();

        Intent intent = new Intent(ProductsActivity.this, CartDetailsActivity.class);
        intent.putExtra("cartItemsDetails", cartItemsDetails);
        intent.putExtra("totalAmount", totalAmountFormatted);
        startActivity(intent);
    }
// ... (rest of the ProductsActivity)

    private ProductModel getProductByName(String productName) {
        for (ProductModel product : arrayList) {
            if (product.getTitle().equals(productName)) {
                return product;
            }
        }
        return null;
    }

    // ... (Other methods)
}
