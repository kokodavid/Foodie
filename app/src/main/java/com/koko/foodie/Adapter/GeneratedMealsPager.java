package com.koko.foodie.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.google.gson.internal.$Gson$Preconditions;
import com.koko.foodie.Models.MealPlan;
import com.koko.foodie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GeneratedMealsPager extends PagerAdapter {

    private List<MealPlan.Meal> mealPlans;
    private Context context;
    private static GeneratedMealsPager.ClickListener clickListener;

    public GeneratedMealsPager(List<MealPlan.Meal>mealPlan,Context context){
        this.mealPlans = mealPlan;
        this.context = context;
    }

    public void setOnClickListener(GeneratedMealsPager.ClickListener clickListener){
        GeneratedMealsPager.clickListener = clickListener;
    }

    @Override
    public int getCount() {
        return mealPlans.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.item_recycler_meal_spoon,
                container,
                false
        );


        ImageView mealThumb = view.findViewById(R.id.mealThumb);
        TextView mealName = view.findViewById(R.id.mealName);
        TextView servings = view.findViewById(R.id.servings);
        TextView readyIn = view.findViewById(R.id.readyIn);
        TextView id = view.findViewById(R.id.id);

        String Image = mealPlans.get(position).getImageType();
        Picasso.get().load("https://spoonacular.com/recipeImages/apple." + Image).into(mealThumb);

        String FoodName = mealPlans.get(position).getTitle();
        mealName.setText(FoodName);

        Integer FoodServings = mealPlans.get(position).getServings();
        servings.setText(Integer.toString(FoodServings));

        Integer ids = mealPlans.get(position).getId();
        id.setText(Integer.toString(ids));

        Integer FoodReadyIn = mealPlans.get(position).getReadyInMinutes();
        readyIn.setText(Integer.toString(FoodReadyIn));



        view.setOnClickListener(v -> {
            clickListener.onClick(v, position);
        });

        container.addView(view, 0);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    public interface ClickListener {
        void onClick(View v, int position);
    }
}
