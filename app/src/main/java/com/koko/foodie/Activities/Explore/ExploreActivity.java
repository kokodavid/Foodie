package com.koko.foodie.Activities.Explore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.koko.foodie.Activities.Search.SearchActivity;
import com.koko.foodie.Activities.UploadRecipeActivity;
import com.koko.foodie.Activities.WinePairing.WinePairingActivity;
import com.koko.foodie.Activities.home.HomeActivity;
import com.koko.foodie.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.hoang8f.android.segmented.SegmentedGroup;

public class ExploreActivity extends AppCompatActivity {

    @BindView(R.id.segmented2)
    SegmentedGroup segmentedGroup;
    @BindView(R.id.addRecipes)
    CardView addRecipes;
    @BindView(R.id.button21)
    RadioButton radioButton2;
    @BindView(R.id.rootView1)
    ConstraintLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        ButterKnife.bind(this);

        SegmentedGroup segmented2 = (SegmentedGroup) rootView.findViewById(R.id.segmented2);
        segmented2.setTintColor(getResources().getColor(R.color.fade));


    }

    public void back(View view) {
        Intent addRecipe = new Intent(ExploreActivity.this, HomeActivity.class);
        addRecipe.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(addRecipe);
    }

    public void addRecipes(View view) {
        Intent addRecipe = new Intent(ExploreActivity.this, UploadRecipeActivity.class);
        addRecipe.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(addRecipe);
    }

    public void WinePairing(View view) {
        Intent addRecipe = new Intent(ExploreActivity.this, WinePairingActivity.class);
        addRecipe.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(addRecipe);
    }

    public void search(View view) {
        Intent addRecipe = new Intent(ExploreActivity.this, SearchActivity.class);
        addRecipe.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(addRecipe);
    }
}