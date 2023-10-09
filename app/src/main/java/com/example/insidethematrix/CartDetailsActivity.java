package com.example.insidethematrix;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CartDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_details);

        TextView textCartItems = findViewById(R.id.textCartItems);
        TextView textTotalAmount = findViewById(R.id.textTotalAmount);

        String cartItemsDetails = getIntent().getStringExtra("cartItemsDetails");
        String totalAmount = getIntent().getStringExtra("totalAmount");

        textCartItems.setText(cartItemsDetails);
        textTotalAmount.setText("Total Amount: " + totalAmount);

        Button btnProceedCheckout = findViewById(R.id.btnProceedToCheckout);

        btnProceedCheckout.setAlpha(0f);
        btnProceedCheckout.setTranslationY(100f);
        btnProceedCheckout.animate()
                .alpha(1f)
                .translationY(0)
                .setDuration(1000)
                .setStartDelay(500)
                .start();

        btnProceedCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartDetailsActivity.this, "Ordered Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
