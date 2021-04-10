package com.example.usgchallange.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.usgchallange.R;
import com.example.usgchallange.model.Meal;
import com.example.usgchallange.view.MealDetailActivity;

import java.util.ArrayList;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Meal> meals;
    SharedPreferences sp;

    public MealsAdapter(Context context,ArrayList<Meal> meals){
        this.context = context;
        this.meals = meals;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.meal_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Meal meal = meals.get(position);
        sp = context.getSharedPreferences("fav",Context.MODE_PRIVATE);
        holder.meal_name.setText(meal.getMealName());
        Glide.with(context).load(meal.getMealImage()).centerInside().placeholder(R.drawable.progress_bar).into(holder.meal_image);
        String isFav=sp.getString(meal.getId(),"false");
        if(isFav.equals("false")){
            Drawable drawable = context.getResources().getDrawable(R.drawable.star);
            holder.fav_button.setBackground(drawable);
        }
        else{
            Drawable drawable = context.getResources().getDrawable(R.drawable.clicked_star);
            holder.fav_button.setBackground(drawable);
        }
        holder.fav_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isFav=sp.getString(meal.getId(),"false");

                if(isFav.equals("false")){
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString(meal.getId(),"true");
                    editor.commit();
                    Drawable drawable = context.getResources().getDrawable(R.drawable.clicked_star);
                    holder.fav_button.setBackground(drawable);
                    Toast.makeText(context,"Marked as favorite",Toast.LENGTH_SHORT).show();
                }
                else{
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString(meal.getId(),"false");
                    editor.commit();
                    Drawable drawable = context.getResources().getDrawable(R.drawable.star);
                    holder.fav_button.setBackground(drawable);
                    Toast.makeText(context,"Unmarked",Toast.LENGTH_SHORT).show();
                }



            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MealDetailActivity.class);
                intent.putExtra("mealId",meal.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView meal_name;
        ImageView meal_image;
        Button fav_button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            meal_image = itemView.findViewById(R.id.meal_image);
            meal_name = itemView.findViewById(R.id.meal_name);
            fav_button = itemView.findViewById(R.id.meal_fav_button);

        }
    }
}
