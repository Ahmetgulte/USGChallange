package com.example.usgchallange.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.os.Handler;
import android.os.Looper;
import android.view.View;


import com.example.usgchallange.R;
import com.example.usgchallange.model.Category;

import com.example.usgchallange.model.MealList;

import com.example.usgchallange.view.adapter.AreaAdapter;
import com.example.usgchallange.view.adapter.CategoryAdapter;
import com.example.usgchallange.viewmodel.MainPageViewModel;
import com.github.ybq.android.spinkit.SpinKitView;


import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    SpinKitView spinKitView;
    SpinKitView spinKitView1;
    RecyclerView areas;
    RecyclerView category_recyclerView;
    MainPageViewModel mainViewModel;
    AreaAdapter areaAdapter;
    CategoryAdapter categoryAdapter;
    private static final String BASE_URL = "https://www.themealdb.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        areas= findViewById(R.id.area_recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        areas.setLayoutManager(linearLayoutManager);
        category_recyclerView = findViewById(R.id.category_recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3,RecyclerView.VERTICAL,false);

        category_recyclerView.setLayoutManager(gridLayoutManager);
        spinKitView = findViewById(R.id.spin_kit);
        spinKitView1 =findViewById(R.id.spin_kit_category);
        areas.setVisibility(View.GONE);
        category_recyclerView.setVisibility(View.GONE);
        mainViewModel=new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(MainPageViewModel.class);
        fetchArea();
        fetchCategory();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Your Code
                category_recyclerView.setVisibility(View.VISIBLE);
                areas.setVisibility(View.VISIBLE);
                spinKitView.setVisibility(View.GONE);
                spinKitView1.setVisibility(View.GONE);
            }
        }, 3000);









    }
    public void fetchArea(){
        mainViewModel.fetchAreaList();
        mainViewModel.getAreas().observe(this, new Observer<List<MealList>>() {
            @Override
            public void onChanged(List<MealList> mealLists) {
                ArrayList<MealList> arrayList = new ArrayList<>(mealLists);
                areaAdapter=new AreaAdapter(MainActivity.this,arrayList);
                areas.setAdapter(areaAdapter);


            }
        });
    }
    public void fetchCategory(){
        mainViewModel.fetchTheCategories();
        mainViewModel.getCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                ArrayList<Category> categories1 = new ArrayList<>(categories);
                categoryAdapter = new CategoryAdapter(MainActivity.this,categories1);
                category_recyclerView.setAdapter(categoryAdapter);

            }
        });
    }
}