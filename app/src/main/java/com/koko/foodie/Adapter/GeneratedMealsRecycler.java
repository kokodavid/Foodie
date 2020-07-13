package com.koko.foodie.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koko.foodie.Models.Food;
import com.koko.foodie.Models.MealPlan;
import com.koko.foodie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GeneratedMealsRecycler extends RecyclerView.Adapter<GeneratedMealsRecycler.RecyclerViewHolder>{
    private List<MealPlan.Meal> meals;
    private Context context;
    private static GeneratedMealsRecycler.ClickListener clickListener;


    public GeneratedMealsRecycler(Context context, List<MealPlan.Meal> meals) {
        this.meals = meals;
        this.context = context;

    }

    @NonNull
    @Override
    public GeneratedMealsRecycler.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.roughxml,
                parent, false);
        return new GeneratedMealsRecycler.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GeneratedMealsRecycler.RecyclerViewHolder holder, int position) {
        String foodImage = meals.get(position).getImageType();
        Picasso.get().load("https://spoonacular.com/recipeImages/apple" + foodImage)
                .placeholder(R.drawable.recipeholder)
                .into(holder.mealThumb);


        String FoodName = meals.get(position).getTitle();
        holder.mealName.setText(FoodName);

        Integer FoodServings = meals.get(position).getServings();
        holder.servings.setText(Integer.toString(FoodServings) + " " + "Serving(s)");

        Integer id = meals.get(position).getId();
        holder.id.setText(Integer.toString(id));


    }

    @Override
    public int getItemCount() {
        return meals.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.mealThumb)
        ImageView mealThumb;
        @BindView(R.id.mealName)
        TextView mealName;
        @BindView(R.id.id)
        TextView id;
        @BindView(R.id.servings)
        TextView servings;

        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }



        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());


        }
    }


    public void setOnItemClickListener(GeneratedMealsRecycler.ClickListener clickListener) {
        GeneratedMealsRecycler.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(View view, int position);
    }
}