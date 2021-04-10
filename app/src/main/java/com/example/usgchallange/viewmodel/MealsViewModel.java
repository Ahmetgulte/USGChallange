package com.example.usgchallange.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.usgchallange.model.Meal;
import com.example.usgchallange.repository.MealRepository;

import java.util.List;

public class MealsViewModel extends AndroidViewModel {

    private MutableLiveData<List<Meal>> mealsByArea = new MutableLiveData<>();
    private MutableLiveData<List<Meal>> mealsByCategory = new MutableLiveData<>();
    private MealRepository mealRepository;
    public MealsViewModel(@NonNull Application application) {
        super(application);
        mealRepository = MealRepository.getInstance();
    }

    public MutableLiveData<List<Meal>> getMealsByArea() {
        mealsByArea = mealRepository.getListOfMealByArea();
        return mealsByArea;
    }

    public MutableLiveData<List<Meal>> getMealsByCategory() {
        mealsByCategory = mealRepository.getListOfMealByCategory();
        return mealsByCategory;
    }

    public void fetchMealByArea(String area){
        mealRepository.fetchMealsByArea(area);
    }
    public void fetchMealByCategory(String category){
        mealRepository.fetchMealsByCategory(category);
    }
}
