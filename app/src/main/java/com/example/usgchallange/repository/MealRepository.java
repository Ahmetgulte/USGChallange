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

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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
    private final CompositeDisposable disposable = new CompositeDisposable();

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
        mealService.getMealList("list")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(ListResponse listResponse) {
                        ArrayList<MealList> mealLists = listResponse.getListOfMeal();
                        listOfMeals.postValue(mealLists);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listOfMeals.postValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    public void fetchTheCategories(){
        mealService.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoryResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CategoryResponse categoryResponse) {
                        ArrayList<Category> categories = categoryResponse.getCategories();
                        listOfCategories.postValue(categories);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listOfCategories.postValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void fetchMealsByArea(String area){
        mealService.getMealsByArea(area)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MealResponse mealResponse) {
                        ArrayList<Meal> meals = mealResponse.getMeals();
                        listOfMealByArea.postValue(meals);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listOfMealByArea.postValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void fetchMealsByCategory(String category){
        mealService.getMealsByCategory(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MealResponse mealResponse) {
                        ArrayList<Meal> meals = mealResponse.getMeals();
                        listOfMealByCategory.postValue(meals);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listOfMealByCategory.postValue(null);
                    }

                    @Override
                    public void onComplete() {

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
