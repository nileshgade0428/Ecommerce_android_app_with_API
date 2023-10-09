package com.example.insidethematrix;

import com.example.insidethematrix.Model.ProductModel;

import java.util.ArrayList;

public class CartActivity {
    private ArrayList<ProductModel> arrayList;

    public CartActivity() {
        arrayList = new ArrayList<>();
    }

    public void addItem(ProductModel product) {
        arrayList.add(product);
    }

    public void removeItem(ProductModel product) {
        arrayList.remove(product);
    }

    public ArrayList<ProductModel> getarrayList() {
        return arrayList;
    }

    public int getSize() {
        return arrayList.size();
    }

    public boolean contains(ProductModel product) {
        return arrayList.contains(product);
    }

}
