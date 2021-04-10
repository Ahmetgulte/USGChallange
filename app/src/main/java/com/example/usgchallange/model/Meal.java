package com.example.usgchallange.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meal {
    @SerializedName("idMeal")
    @Expose
    private String id;
    @SerializedName("strMeal")
    @Expose
    private String mealName;
    @SerializedName("strMealThumb")
    @Expose
    private String mealImage;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealImage() {
        return mealImage;
    }

    public void setMealImage(String mealImage) {
        this.mealImage = mealImage;
    }


}
