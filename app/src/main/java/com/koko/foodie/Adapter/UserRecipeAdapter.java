package com.koko.foodie.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koko.foodie.Models.uploadData;
import com.koko.foodie.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserRecipeAdapter extends RecyclerView.Adapter<UserRecipeAdapter.ViewHolder> {

    private List<uploadData> userFood = new ArrayList<>();

    public UserRecipeAdapter(Context baseContext, List<uploadData> fRecipes) {
            this.userFood = fRecipes;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View item = layoutInflater.inflate(R.layout.item_recycler_meal_spoon, parent, false);
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        uploadData model = userFood.get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return userFood.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mealThumb)
        ImageView mealThumb;
        @BindView(R.id.mealName)
        TextView mealName;
        @BindView(R.id.id)
        TextView id;
        @BindView(R.id.servings)
        TextView servings;
        @BindView(R.id.readyIn)
        TextView readyIn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(uploadData model) {
            String img_url = model.getImg();
            Picasso.get().load(img_url)
                    .centerCrop()
                    .resize(600,200)
                    .placeholder(R.drawable.ic_circle)
                    .into(mealThumb);

            mealName.setText(model.getName());
            servings.setText(model.getCount());
            readyIn.setText(model.getTime());
        }
    }
}
