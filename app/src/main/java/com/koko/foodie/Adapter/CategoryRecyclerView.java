package com.koko.foodie.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koko.foodie.Models.Categories;
import com.koko.foodie.Models.Meals;
import com.koko.foodie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryRecyclerView extends RecyclerView.Adapter<CategoryRecyclerView.RecyclerViewHolder> {

    private List<Categories.Category> categories;
    private Context context;
    private static ClickListener clickListener;

    public CategoryRecyclerView(List<Categories.Category> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryRecyclerView.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_pager_header,
                viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerView.RecyclerViewHolder viewHolder, int i) {

        String strCategoryThumb = categories.get(i).getStrCategoryThumb();
        Picasso.get().load(strCategoryThumb).placeholder(R.drawable.ic_circle).into(viewHolder.categoryThumb);

        String strMealName = categories.get(i).getStrCategory();
        viewHolder.categoryName.setText(strMealName);
    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.mealThumb)
        ImageView categoryThumb;
        @BindView(R.id.mealName)
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
        CategoryRecyclerView.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}
