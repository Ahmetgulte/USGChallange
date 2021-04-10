package com.example.usgchallange.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListResponse {
    @SerializedName("meals")
    @Expose
    private ArrayList<MealList> listOfMeal;

    public ArrayList<MealList> getListOfMeal() {
        return listOfMeal;
    }

    public void setListOfMeal(ArrayList<MealList> listOfMeal) {
        this.listOfMeal = listOfMeal;
    }
}
