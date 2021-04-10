package com.example.usgchallange.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.usgchallange.R;
import com.example.usgchallange.model.Meal;
import com.example.usgchallange.view.adapter.MealsAdapter;
import com.example.usgchallange.viewmodel.MealsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MealsActivity extends AppCompatActivity {
    String name;
    String query;
    RecyclerView recyclerView;
    MealsAdapter mealsAdapter;
    MealsViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        query = intent.getStringExtra("query");
        setTitle(name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }
    @Override
    protected void onResume() {
        super.onResume();
        recyclerView = findViewById(R.id.meals_recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);


        viewModel=new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(MealsViewModel.class);
        if(query.equals("a")){
            fetchMealByArea(name);
        }
        else {
            fetchMealByCategory(name);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    public void fetchMealByArea(String name){
        viewModel.fetchMealByArea(name);
        viewModel.getMealsByArea().observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                ArrayList<Meal> meals1 = new ArrayList<>(meals);
                mealsAdapter = new MealsAdapter(MealsActivity.this,meals1);
                recyclerView.setAdapter(mealsAdapter);
            }
        });
    }
    public void fetchMealByCategory(String name){
        viewModel.fetchMealByCategory(name);
        viewModel.getMealsByCategory().observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                ArrayList<Meal> meals1 = new ArrayList<>(meals);
                mealsAdapter = new MealsAdapter(MealsActivity.this,meals1);
                recyclerView.setAdapter(mealsAdapter);
            }
        });
    }
}