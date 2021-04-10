package com.example.usgchallange.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.usgchallange.R;
import com.example.usgchallange.view.adapter.PagerAdapter;
import com.example.usgchallange.volley.MealDetail;
import com.example.usgchallange.volley.MealDetailRequest;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MealDetailActivity extends AppCompatActivity {
    Button favButton;
    ImageView mealImage;
    TextView mealName;
    TextView mealCategory;
    TextView mealArea;
    TabLayout tabLayout;
    TabItem tabIngredient;
    TabItem tabInstructor;
    ViewPager viewPager;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);
        setTitle(getString(R.string.meal_detail_bar_title));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        favButton =findViewById(R.id.meal_details_fav);
        mealImage = findViewById(R.id.meal_details_image);
        mealName = findViewById(R.id.meal_details_name);
        mealCategory = findViewById(R.id.meal_details_category);
        mealArea = findViewById(R.id.meal_details_area);
        tabLayout = findViewById(R.id.tab_layout);
        tabIngredient = findViewById(R.id.tab_ingredient);
        tabInstructor = findViewById(R.id.tab_instructor);
        viewPager = findViewById(R.id.view_pager);
        sp = getApplicationContext().getSharedPreferences("fav", Context.MODE_PRIVATE);
        Intent intent = getIntent();
        String mealId = intent.getStringExtra("mealId");
        MealDetailRequest request = new MealDetailRequest(this);
        request.getMealDetail(mealId, new MealDetailRequest.VolleyResponseListener() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onResponse(MealDetail mealDetail) {

                Glide.with(MealDetailActivity.this).load(mealDetail.getMealImage()).centerCrop().into(mealImage);
                mealName.setText(mealDetail.getMealName());
                mealCategory.setText(mealDetail.getCategory());
                mealArea.setText(mealDetail.getArea());
                String isFav=sp.getString(mealDetail.getMealId(),"false");
                if(isFav.equals("false")){
                    Drawable drawable = getResources().getDrawable(R.drawable.star);
                    favButton.setBackground(drawable);
                }
                else{
                    Drawable drawable = getResources().getDrawable(R.drawable.clicked_star);
                    favButton.setBackground(drawable);
                }

                favButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setFavorite(mealDetail);
                    }
                });
                mealImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent youtubeIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mealDetail.getYoutubeUrl()));
                        try{
                            if (youtubeIntent.resolveActivity(getPackageManager()) != null) {
                                startActivity(youtubeIntent);
                            } else {
                                Log.d("ImplicitIntents", "Can't handle this intent!");
                            }

                        }
                        catch (ActivityNotFoundException ex){

                        }
                    }
                });
                PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(),MealDetailActivity.this,
                        tabLayout.getTabCount(),mealDetail);
                viewPager.setAdapter(pagerAdapter);

                tabLayout.setupWithViewPager(viewPager);


            }
        });




    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void setFavorite(MealDetail meal){
        String isFav=sp.getString(meal.getMealId(),"false");

        if(isFav.equals("false")){
            SharedPreferences.Editor editor=sp.edit();
            editor.putString(meal.getMealId(),"true");
            editor.commit();
            Drawable drawable = getResources().getDrawable(R.drawable.clicked_star);
            favButton.setBackground(drawable);
            Toast.makeText(this,"Marked as favorite",Toast.LENGTH_SHORT).show();
        }
        else{
            SharedPreferences.Editor editor=sp.edit();
            editor.putString(meal.getMealId(),"false");
            editor.commit();
            Drawable drawable = getResources().getDrawable(R.drawable.star);
            favButton.setBackground(drawable);
            Toast.makeText(this,"Unmarked",Toast.LENGTH_SHORT).show();
        }
    }
}