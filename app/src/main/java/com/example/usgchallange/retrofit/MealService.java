package com.example.usgchallange.retrofit;



import com.example.usgchallange.model.CategoryResponse;
import com.example.usgchallange.model.ListResponse;
import com.example.usgchallange.model.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {

    @GET("/api/json/v1/1/list.php")
    Call<ListResponse> getMealList(@Query("a") String query);

    @GET("/api/json/v1/1/categories.php")
    Call<CategoryResponse> getCategories();

    @GET("/api/json/v1/1/filter.php")
    Call<MealResponse> getMealsByArea(@Query("a") String area);

    @GET("/api/json/v1/1/filter.php")
    Call<MealResponse> getMealsByCategory(@Query("c") String category);
    @GET("/api/json/v1/1/lookup.php")
    Call<String> getMealDetails(@Query("i") String mealId);
}
