package com.koko.foodie.Activities.UploadedRecipes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.koko.foodie.Adapter.UserRecipeAdapter;
import com.koko.foodie.Models.uploadData;
import com.koko.foodie.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FirebaseActivity extends AppCompatActivity  {

    private DatabaseReference mRecipes;
    private ValueEventListener mRecipesReferenceListener;
    private FirebaseRecyclerAdapter<uploadData, FirebaseRecipeViewHolder> mFirebaseAdapter;

    @BindView(R.id.firebaseRecycler) RecyclerView mRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);
        ButterKnife.bind(this);

        mRecipes = FirebaseDatabase.getInstance().getReference("Recipes");
        setUpFirebaseAdapter();





//        mRecipes.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot recipeSnapshot : dataSnapshot.getChildren()){
//                    String recipe = recipeSnapshot.getValue().toString();
//                    Log.d("Recipe",recipe);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }

    private void setUpFirebaseAdapter() {
        FirebaseRecyclerOptions<uploadData> options =
                new FirebaseRecyclerOptions.Builder<uploadData>()
                .setQuery(mRecipes,uploadData.class)
                .build();


        mFirebaseAdapter = new FirebaseRecyclerAdapter<uploadData, FirebaseRecipeViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseRecipeViewHolder firebaseRecipeViewHolder, int i, @NonNull uploadData uploadData) {
                firebaseRecipeViewHolder.bindRecipe(uploadData);
            }

            @NonNull
            @Override
            public FirebaseRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_meal_spoon_one, parent, false);
                return new FirebaseRecipeViewHolder(view);
            }
        };
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setClipToPadding(false);
        mRecyclerView.setAdapter(mFirebaseAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();

        }
    }





}