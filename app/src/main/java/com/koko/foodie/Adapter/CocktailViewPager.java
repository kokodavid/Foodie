package com.koko.foodie.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.koko.foodie.Activities.Cocktails.Cocktail;
import com.koko.foodie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CocktailViewPager extends PagerAdapter {

    private List<Cocktail.Drink> cocktail;
    private Context context;
    private static CocktailViewPager.ClickListener clickListener;


    public CocktailViewPager(List<Cocktail.Drink>cocktail,Context context){
        this.cocktail = cocktail;
        this.context = context;
    }

    public void setOnItemClickListener(CocktailViewPager.ClickListener clickListener) {
        CocktailViewPager.clickListener = clickListener;
    }

    @Override
    public int getCount() {
        return cocktail.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.item_viewpager_cocktail,
                container,
                false
        );

        ImageView cocktailThumb = view.findViewById(R.id.cocktailThumb);
        TextView cocktailName = view.findViewById(R.id.cocktailName);

        String strDrinkThumb = cocktail.get(position).getStrDrinkThumb();
        Picasso.get().load(strDrinkThumb).into(cocktailThumb);

        String strDrinkName = cocktail.get(position).getStrDrink();
        cocktailName.setText(strDrinkName);


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
