package com.example.insidethematrix.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryModel {
    @SerializedName("categories")
    @Expose
    private String categories;
    /**
     *
     * @param categories
     */
    public CategoryModel(String categories) {
        this.categories = categories;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }
}
