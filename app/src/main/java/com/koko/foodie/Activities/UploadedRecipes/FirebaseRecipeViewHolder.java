package com.koko.foodie.Activities.UploadedRecipes;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.koko.foodie.Models.uploadData;
import com.koko.foodie.R;
import com.koko.foodie.SharedPref;
import com.squareup.picasso.Picasso;

public class FirebaseRecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;
    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_DETAIL = "position";

    SharedPref sharedPref = new SharedPref();

    uploadData recipe;

    public  FirebaseRecipeViewHolder(View itemView){
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);

        }


     public void bindRecipe(uploadData recipe){


        this.recipe = recipe;

         ImageView recipeImage = (ImageView) mView.findViewById(R.id.mealThumb);
         TextView recipeName = (TextView) mView.findViewById(R.id.mealName);
         TextView readyIn = (TextView) mView.findViewById(R.id.readyIn);
         TextView serving = (TextView) mView.findViewById(R.id.servings);





         TextView procedure = (TextView) mView.findViewById(R.id.procedure);
         TextView uploadedBy = (TextView) mView.findViewById(R.id.uploadedBy);
         TextView ingredients = (TextView) mView.findViewById(R.id.ingredients);


        sharedPref.initSharedPref(mContext);





         Picasso.get().load(recipe.getImg()).placeholder(R.drawable.recipeholder).into(recipeImage);
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
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        TextView foodName = v.findViewById(R.id.mealName);
        TextView serving = v.findViewById(R.id.readyIn);
        TextView readyIn = v.findViewById(R.id.servings);
        TextView ingredients = v.findViewById(R.id.ingredients);
        TextView procedure = v.findViewById(R.id.procedure);
        TextView uploadedBy = v.findViewById(R.id.uploadedBy);


        sharedPref.writeStr("image", recipe.getImg());


        intent.putExtra("serving", serving.getText());
        intent.putExtra("ingredients", ingredients.getText());
        intent.putExtra("procedure", procedure.getText());
        intent.putExtra("uploadedBy", uploadedBy.getText());

        intent.putExtra("name",foodName.getText());
        intent.putExtra("readyIn",readyIn.getText());

    mContext.startActivity(intent);

    }
    }



