package com.koko.foodie.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koko.foodie.Activities.Favorite.Favorite;
import com.koko.foodie.Common;
import com.koko.foodie.Models.Meals;
import com.koko.foodie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewMealByCategory extends RecyclerView.Adapter<RecyclerViewMealByCategory.RecyclerViewHolder> {

    private List<Meals.Meal> meals;
    private Context context;
    private static ClickListener clickListener;

    public RecyclerViewMealByCategory(Context context, List<Meals.Meal> meals) {
        this.meals = meals;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewMealByCategory.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_meal,viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewMealByCategory.RecyclerViewHolder viewHolder, int i) {

        String strMealThumb = meals.get(i).getStrMealThumb();
        Picasso.get().load(strMealThumb).placeholder(R.drawable.shadow_bottom_to_top).into(viewHolder.mealThumb);

        String strMealName = meals.get(i).getStrMeal();
        viewHolder.mealName.setText(strMealName);

        if (Common.favoriteRespository.isFavorite(Integer.parseInt(meals.get(i).getIdMeal())) == 1) {
            viewHolder.fav.setImageResource(R.drawable.ic_favorite);
        }else
            viewHolder.fav.setImageResource(R.drawable.ic_favorite_border);

        viewHolder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Common.favoriteRespository.isFavorite(Integer.parseInt(meals.get(i).getIdMeal())) != 1)
                {
                    addOrRemoveFavorites(meals.get(i),true);
                    viewHolder.fav.setImageResource(R.drawable.ic_favorite_border);
                }else{
                    addOrRemoveFavorites(meals.get(i),false);
                    viewHolder.fav.setImageResource(R.drawable.ic_favorite);
                }
            }
        });

    }

    private void addOrRemoveFavorites(Meals.Meal meal, boolean b) {
        Favorite favorite = new Favorite();
        favorite.ID = meal.getIdMeal();
        favorite.image = meal.getStrMealThumb();
        favorite.name = meal.getStrMeal();

        if (b)
            Common.favoriteRespository.insertFav(favorite);
        else
            Common.favoriteRespository.delete(favorite);
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
        @BindView(R.id.love)
        ImageView fav;
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


    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerViewMealByCategory.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}