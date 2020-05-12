package com.koko.foodie.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.koko.foodie.Models.Categories;
import com.koko.foodie.Models.Meals;
import com.koko.foodie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;

public class LatestViewPager extends PagerAdapter {

    private List<Meals.Meal> meals;
    private Context context;
    private static LatestViewPager.ClickListener clickListener;

    public LatestViewPager(List<Meals.Meal> meals, Context context) {
        this.meals = meals;
        this.context = context;
    }

    public void setOnItemClickListener(LatestViewPager.ClickListener clickListener) {
        LatestViewPager.clickListener = clickListener;
    }

    @Override
    public int getCount() {
        return meals.size();

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.item_recycler_category,
                container,
                false
        );

        ImageView mealThumb = view.findViewById(R.id.mealThumb);
        TextView mealName = view.findViewById(R.id.mealName);

        String strMealThumb = meals.get(position).getStrMealThumb();
        Picasso.get().load(strMealThumb).into(mealThumb);

        String strMealName = meals.get(position).getStrMeal();
        mealName.setText(strMealName);


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
