package com.koko.foodie.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koko.foodie.Activities.Cocktails.Cocktail;
import com.koko.foodie.Models.Meals;
import com.koko.foodie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CocktailRecyclerAdapter extends RecyclerView.Adapter<CocktailRecyclerAdapter.RecyclerViewHolder> {

    private List<Cocktail.Drink> cocktail;
    private Context context;
    private static ClickListener clickListener;

    public CocktailRecyclerAdapter(List<Cocktail.Drink>cocktail,Context context){
        this.cocktail = cocktail;
        this.context = context;
    }
    @NonNull
    @Override
    public CocktailRecyclerAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_viewpager_cocktail,
                viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CocktailRecyclerAdapter.RecyclerViewHolder viewHolder, int i) {

        String strCategoryThumb = cocktail.get(i).getStrDrinkThumb();
        Picasso.get().load(strCategoryThumb).placeholder(R.drawable.ic_circle).into(viewHolder.categoryThumb);

        String strMealName = cocktail.get(i).getStrDrink();
        viewHolder.categoryName.setText(strMealName);
    }


    @Override
    public int getItemCount() {
        return cocktail.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.cocktailThumb)
        ImageView categoryThumb;
        @BindView(R.id.cocktailName)
        TextView categoryName;
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
        CocktailRecyclerAdapter.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}
