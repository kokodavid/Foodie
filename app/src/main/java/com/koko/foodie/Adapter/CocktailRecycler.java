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
import com.koko.foodie.Models.Food;
import com.koko.foodie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CocktailRecycler extends RecyclerView.Adapter<CocktailRecycler.RecyclerViewHolder> {

    private List<Cocktail.Drink> food;
    private Context context;
    private static CocktailRecycler.ClickListener clickListener;


    public CocktailRecycler(Context context, List<Cocktail.Drink> food) {
        this.food = food;
        this.context = context;

    }

    @NonNull
    @Override
    public CocktailRecycler.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_viewpager_cocktail_one,
                parent, false);
        return new CocktailRecycler.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CocktailRecycler.RecyclerViewHolder holder, int position) {

     String Cocktailname = food.get(position).getStrDrink();
     holder.cocktailName.setText( Cocktailname);

        String CocktailCategory = food.get(position).getStrCategory();
        holder.cocktailCategory.setText("Category:"+" "+CocktailCategory);

     String CocktailImage = food.get(position).getStrDrinkThumb();
     Picasso.get().load(CocktailImage).into(holder.cocktailThumb);

    }

    @Override
    public int getItemCount() {
        return food.size();
    }




    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.cocktailThumb)
        ImageView cocktailThumb;
        @BindView(R.id.cocktailName)
        TextView cocktailName;
        @BindView(R.id.cocktailCategory)
        TextView cocktailCategory;


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


    public void setOnItemClickListener(CocktailRecycler.ClickListener clickListener) {
        CocktailRecycler.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(View view, int position);
    }
}