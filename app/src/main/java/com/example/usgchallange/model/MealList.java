package com.example.usgchallange.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MealList {
    @SerializedName("strArea")
    @Expose
    private String area;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
