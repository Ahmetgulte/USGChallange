package com.example.usgchallange.volley;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MealDetail {
    private ArrayList<String> ingredients;
    private ArrayList<String> measures;
    private String mealName;
    private String category;
    private String area;
    private String youtubeUrl;
    private String mealImage;
    private String instructions;

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    private String mealId;

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getMealImage() {
        return mealImage;
    }

    public void setMealImage(String mealImage) {
        this.mealImage = mealImage;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public ArrayList<String> getMeasures() {
        return measures;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setMeasures(ArrayList<String> measures) {
        this.measures = measures;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public String getMealName() {
        return mealName;
    }

    public String getCategory() {
        return category;
    }

    public String getArea() {
        return area;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }
}
