package com.koko.foodie.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.koko.foodie.Models.TestModel;
import com.koko.foodie.Models.TestModelB;
import com.koko.foodie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodDetailsAdapter extends PagerAdapter {
    private List<TestModelB> details;
    private Context context;

    public FoodDetailsAdapter(List<TestModelB> details, Context context) {
        this.details = details;
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.item_food_detail,
                container,
                false
        );

        ImageView DetailImage = view.findViewById(R.id.FoodImage);


        String Image = details.get(position).getImage();
        Picasso.get().load("https://spoonacular.com/recipeImages/" + Image).into(DetailImage);

        container.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }




    @Override
    public int getCount() {
        return details.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
}
