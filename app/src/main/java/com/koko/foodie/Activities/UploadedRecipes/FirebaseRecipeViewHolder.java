package com.koko.foodie.Activities.UploadedRecipes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.koko.foodie.Activities.FoodDetails.FoodDetailActivity;
import com.koko.foodie.Activities.home.HomeActivity;
import com.koko.foodie.Models.Food;
import com.koko.foodie.Models.uploadData;
import com.koko.foodie.R;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class FirebaseRecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;
    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_DETAIL = "position";

    public  FirebaseRecipeViewHolder(View itemView){
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);

        }


     public void bindRecipe(uploadData recipe){
         ImageView recipeImage = (ImageView) mView.findViewById(R.id.mealThumb);
         TextView recipeName = (TextView) mView.findViewById(R.id.mealName);
         TextView readyIn = (TextView) mView.findViewById(R.id.readyIn);
         TextView serving = (TextView) mView.findViewById(R.id.servings);


         TextView procedure = (TextView) mView.findViewById(R.id.procedure);
         TextView uploadedBy = (TextView) mView.findViewById(R.id.uploadedBy);
         TextView ingredients = (TextView) mView.findViewById(R.id.ingredients);


         Picasso.get().load(recipe.getImg()).into(recipeImage);
         recipeName.setText(recipe.getName());
         readyIn.setText(recipe.getTime());
         serving.setText(recipe.getCount());
         procedure.setText(recipe.getProcedure());
         uploadedBy.setText(recipe.getUploaded_by());
         ingredients.setText(recipe.getIngredients());
     }

    @Override
    public void onClick(View v) {


        Intent intent = new Intent(mContext, FirebaseDetailActivity.class);

        TextView foodName = v.findViewById(R.id.mealName);
        TextView serving = v.findViewById(R.id.readyIn);
        TextView readyIn = v.findViewById(R.id.servings);
        TextView ingredients = v.findViewById(R.id.ingredients);
        TextView procedure = v.findViewById(R.id.procedure);
        TextView uploadedBy = v.findViewById(R.id.uploadedBy);

        intent.putExtra("serving", serving.getText());
        intent.putExtra("ingredients", ingredients.getText());
        intent.putExtra("procedure", procedure.getText());
        intent.putExtra("uploadedBy", uploadedBy.getText());

        intent.putExtra("name",foodName.getText());
        intent.putExtra("readyIn",readyIn.getText());

    mContext.startActivity(intent);

    }
    }



