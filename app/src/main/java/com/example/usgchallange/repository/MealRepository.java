package com.example.usgchallange.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.usgchallange.model.Category;
import com.example.usgchallange.model.CategoryResponse;
import com.example.usgchallange.model.ListResponse;
import com.example.usgchallange.model.Meal;
import com.example.usgchallange.volley.MealDetail;
import com.example.usgchallange.model.MealList;
import com.example.usgchallange.model.MealResponse;
import com.example.usgchallange.retrofit.MealService;
import com.example.usgchallange.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MealRepository {
    private static MealRepository mealRepository;
    private static MealService mealService;
    MutableLiveData<List<MealList>> listOfMeals;
    MutableLiveData<List<Category>> listOfCategories;
    MutableLiveData<List<Meal>> listOfMealByArea;
    MutableLiveData<List<Meal>> listOfMealByCategory;


    public static MealRepository getInstance(){
        if(mealRepository == null)
            mealRepository = new MealRepository();
        return mealRepository;
    }
    public MealRepository (){
        mealService = RetrofitService.getMealService();
        listOfMeals = new MutableLiveData<>();
        listOfCategories = new MutableLiveData<>();
        listOfMealByArea = new MutableLiveData<>();
        listOfMealByCategory = new MutableLiveData<>();
    }

    public void searchAreaList(){
        mealService.getMealList("list").enqueue(new Callback<ListResponse>() {
            @Override
            public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {
                if(response.body() != null){
                    listOfMeals.postValue(response.body().getListOfMeal());
                }
            }

            @Override
            public void onFailure(Call<ListResponse> call, Throwable t) {
                Log.e("Tag","asd");
                listOfMeals.postValue(null);
            }
        });
    }
    public void fetchTheCategories(){
        mealService.getCategories().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if(response.body() != null){
                    listOfCategories.postValue(response.body().getCategories());
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                listOfCategories.postValue(null);
            }
        });
    }
    public void fetchMealsByArea(String area){
        mealService.getMealsByArea(area).enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.body() != null){
                    listOfMealByArea.postValue(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                listOfMealByArea.postValue(null);
            }
        });
    }
    public void fetchMealsByCategory(String category){
        mealService.getMealsByCategory(category).enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.body() != null){
                    listOfMealByCategory.postValue(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                listOfMealByCategory.postValue(null);
            }
        });
    }



    // returns the list
    public MutableLiveData<List<MealList>> getListOfMeals() {
        return listOfMeals;
    }

    public MutableLiveData<List<Category>> getListOfCategories() {
        return listOfCategories;
    }

    public MutableLiveData<List<Meal>> getListOfMealByArea() {
        return listOfMealByArea;
    }

    public MutableLiveData<List<Meal>> getListOfMealByCategory() {
        return listOfMealByCategory;
    }
}
