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
import com.koko.foodie.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.RecyclerViewHolder>{

    private List<Meals.Meal> mealList;
    private Context context;
    private static SearchAdapter.ClickListener clickListener;


    public SearchAdapter(List<Meals.Meal> meals, Context context ) {
        this.mealList = meals;
        this.context = context;
        mealList = new ArrayList<>(meals);
    }


    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_item,
                parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        String strImage = mealList.get(position).getStrMealThumb();
        Picasso.get().load(strImage).placeholder(R.drawable.ic_circle).into(holder.searchImage);

        String strMealName = mealList.get(position).getStrMeal();
        holder.searchName.setText(strMealName);
    }

    @Override
    public int getItemCount() {
        return mealList.size();

    }




    public void setOnItemClickListener(ClickListener clickListener) {
        SearchAdapter.clickListener = (SearchAdapter.ClickListener) clickListener;
    }

    public void filteredList(ArrayList<Meals.Meal> filterList) {
        mealList.clear();
        mealList.addAll(filterList);
        this.notifyDataSetChanged();

    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.searchThumb)
        ImageView searchImage;
        @BindView(R.id.searchName)
        TextView searchName;
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


    public interface ClickListener {
        void onClick(View view, int position);
    }
}
