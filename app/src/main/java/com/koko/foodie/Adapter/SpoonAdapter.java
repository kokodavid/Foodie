package com.koko.foodie.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koko.foodie.Activities.Search.SearchActivity;
import com.koko.foodie.Models.Meals;
import com.koko.foodie.Models.SpoonMeals;
import com.koko.foodie.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SpoonAdapter extends RecyclerView.Adapter<SpoonAdapter.RecyclerViewHolder> {

    private List<SpoonMeals.Result> food;
    private Context context;


    public SpoonAdapter(List<SpoonMeals.Result> food, Context context) {
        this.food = food;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_meal_spoon,
                parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        String foodImage = food.get(position).getImage();
        Picasso.get().load(foodImage).placeholder(R.drawable.ic_circle).into(holder.mealThumb);

        String FoodName = food.get(position).getTitle();
        holder.mealName.setText(FoodName);

        Integer FoodServings = food.get(position).getServings();
        holder.servings.setText(Integer.toString(FoodServings));

        Integer FoodReadyIn = food.get(position).getReadyInMinutes();
        holder.readyIn.setText(Integer.toString(FoodReadyIn));
    }

    @Override
    public int getItemCount() {
        return food.size();

    }


    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mealThumb)
        ImageView mealThumb;
        @BindView(R.id.mealName)
        TextView mealName;
        @BindView(R.id.servings)
        TextView servings;
        @BindView(R.id.readyIn)
        TextView readyIn;

        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


        public interface ClickListener {
            void onClick(View view, int position);
        }
    }
}
