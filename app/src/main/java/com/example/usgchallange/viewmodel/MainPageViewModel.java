package com.example.usgchallange.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.usgchallange.model.Category;
import com.example.usgchallange.model.MealList;
import com.example.usgchallange.repository.MealRepository;

import java.util.List;

public class MainPageViewModel extends AndroidViewModel {
    private MealRepository mealRepository;
    private MutableLiveData<List<MealList>> areas = new MutableLiveData<>();
    private MutableLiveData<List<Category>> categories = new MutableLiveData<>();

    public MainPageViewModel(@NonNull Application application) {
        super(application);
        mealRepository = MealRepository.getInstance();
    }

    public MutableLiveData<List<Category>> getCategories() {
        categories = mealRepository.getListOfCategories();
        return categories;
    }

    public MutableLiveData<List<MealList>> getAreas() {
        areas = mealRepository.getListOfMeals();
        return areas;
    }
    public void fetchAreaList(){
        mealRepository.searchAreaList();
    }

    public void fetchTheCategories(){
        mealRepository.fetchTheCategories();
    }
}
