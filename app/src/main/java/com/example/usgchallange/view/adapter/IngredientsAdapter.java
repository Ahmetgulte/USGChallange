package com.example.usgchallange.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usgchallange.R;

import java.util.ArrayList;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder>{

    private Context context;
    private ArrayList<String> ingredients;
    private ArrayList<String> measures;


    public IngredientsAdapter(Context context, ArrayList<String> ingredients, ArrayList<String> measures) {
        this.context = context;
        this.ingredients = ingredients;
        this.measures = measures;



    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ingredients_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


            holder.instruction.setText(ingredients.get(position));
            holder.measure.setText(measures.get(position));



    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView instruction;
        TextView measure;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            instruction = itemView.findViewById(R.id.instructor_text_);
            measure =itemView.findViewById(R.id.measure);
        }
    }
}
