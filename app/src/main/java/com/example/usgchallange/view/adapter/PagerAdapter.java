package com.example.usgchallange.view.adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.usgchallange.R;
import com.example.usgchallange.view.IngredientsFragment;
import com.example.usgchallange.view.InstructorFragment;
import com.example.usgchallange.volley.MealDetail;

public class PagerAdapter extends FragmentPagerAdapter {
    private  int numOfTabs;
    private Context context;
    private MealDetail mealDetail;
    public PagerAdapter(FragmentManager fm, Context context, int numOfTabs, MealDetail mealDetail){
        super(fm);
        this.numOfTabs = numOfTabs;
        this.mealDetail = mealDetail;
        this.context = context;

    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();

        switch (position){
            case 0 :
                bundle.putStringArrayList("ingredients",mealDetail.getIngredients());
                bundle.putStringArrayList("measures",mealDetail.getMeasures());
                IngredientsFragment ingredientsFragment = new IngredientsFragment();
                ingredientsFragment.setArguments(bundle);
                return ingredientsFragment;
            case 1 :
                bundle.putString("data",mealDetail.getInstructions());
                InstructorFragment instructorFragment = new InstructorFragment();
                instructorFragment.setArguments(bundle);
                return  instructorFragment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
    @Override
    public CharSequence getPageTitle(int position)
    {
        String title = null;
        if (position == 0)
            title = context.getResources().getString(R.string.ingredient);
        else if (position == 1)
            title = context.getResources().getString(R.string.instruction);

        return title;
    }
}
