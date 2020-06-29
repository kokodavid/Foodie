package com.koko.foodie.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koko.foodie.Models.MealPlan;
import com.koko.foodie.Models.TestModelB;
import com.koko.foodie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientsRecycler extends RecyclerView.Adapter<IngredientsRecycler.RecyclerViewHolder> {
    private List<TestModelB.ExtendedIngredient> fodie;
    private List<TestModelB.AnalyzedInstruction> instructions;
    private String image;
    private Context context;


    public IngredientsRecycler(List<TestModelB.ExtendedIngredient> fodie,List<TestModelB.AnalyzedInstruction> instructions, Context context,String image) {
        this.fodie = fodie;
        this.instructions = instructions;
        this.image = image;
        this.context = context;
    }

    @NonNull
    @Override
    public IngredientsRecycler.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ingredient_item,
                parent, false);
        return new IngredientsRecycler.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsRecycler.RecyclerViewHolder holder, int position) {

        String Image = fodie.get(position).getImage();
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_250x250/" + Image)
                .placeholder(R.drawable.sample_image_meal)
                .into(holder.DetailImage);

        String FoodName = fodie.get(position).getName();
        holder.DetailName.setText(FoodName);

        String Unit = fodie.get(position).getUnit();
        holder.DetailUnit.setText(Unit);

        Float amount = fodie.get(position).getAmount();
        holder.DetailAmount.setText(Float.toString(amount));


    }

    @Override
    public int getItemCount() {
        return fodie.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.detailImage)
        ImageView DetailImage;
        @BindView(R.id.detailName)
        TextView DetailName;
        @BindView(R.id.unit)
        TextView DetailUnit;
        @BindView(R.id.amount)
        TextView DetailAmount;

        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }




    }


}
