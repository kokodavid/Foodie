package com.koko.foodie.Activities.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.koko.foodie.Activities.profile.UserRecipeViewHolder;
import com.koko.foodie.Models.uploadData;
import com.koko.foodie.R;
import com.koko.foodie.Utils.Preferences;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserProfileActivity extends AppCompatActivity implements UserProfileView {


    @BindView(R.id.user_recipes)
    RecyclerView recyclerView;

    DatabaseReference userRecipes;
    private FirebaseRecyclerAdapter<uploadData, UserRecipeViewHolder> mFirebaseAdapter;
    Query users;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ButterKnife.bind(this);


//        presenter.getUserRecipes();
        String uid = Preferences.getUid(this);
        userRecipes = FirebaseDatabase.getInstance().getReference("Recipes");
        users = userRecipes.orderByChild("uid").equalTo(uid);



        setUpfirebaseAdapter();

    }

    private void setUpfirebaseAdapter() {
        FirebaseRecyclerOptions<uploadData> options = new FirebaseRecyclerOptions.Builder<uploadData>().setQuery(users, uploadData.class).build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<uploadData, UserRecipeViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull UserRecipeViewHolder firebaseRecipeViewHolder, int i, @NonNull uploadData uploadData) {
                firebaseRecipeViewHolder.bindRecipe(uploadData);
            }

            @NonNull
            @Override
            public UserRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_meal_spoon_one, parent, false);
                return new UserRecipeViewHolder(view);
            }
        };
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(mFirebaseAdapter);
    }
    @Override
    public void setUserinfo(FirebaseUser user) {
        //set User info

    }

    @Override
    public void showLoading() {
        findViewById(R.id.shimmerMeal2).setVisibility(View.VISIBLE);

    }

    @Override
    public void hideloading() {
        findViewById(R.id.shimmerMeal2).setVisibility(View.GONE);

    }

    @Override
    public void onErrorLoading(String message) {

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



/*
    @Override
    public void setUserRecipes(ArrayList<DataSnapshot> userRecipes) {
        user_recipes.addAll(userRecipes);

    }*/

}
