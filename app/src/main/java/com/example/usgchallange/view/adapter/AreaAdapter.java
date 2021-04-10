package com.example.usgchallange.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usgchallange.R;
import com.example.usgchallange.model.MealList;
import com.example.usgchallange.view.MealsActivity;

import java.util.ArrayList;

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.AreaViewHolder> {
    private Context context;
    private ArrayList<MealList> areaList;

    public AreaAdapter(Context context, ArrayList<MealList> areaList){
        this.context = context;
        this.areaList = areaList;
    }

    @NonNull
    @Override
    public AreaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.area_list,parent,false);
        return new AreaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AreaViewHolder holder, int position) {
        MealList mealList= areaList.get(position);
        holder.areaName.setText(mealList.getArea());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MealsActivity.class);
                intent.putExtra("name",mealList.getArea());
                intent.putExtra("query","a");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return areaList.size();
    }

    class AreaViewHolder extends RecyclerView.ViewHolder{

        TextView areaName;
        public AreaViewHolder(@NonNull View itemView) {
            super(itemView);
            areaName = itemView.findViewById(R.id.area_item_name);
        }
    }
}
