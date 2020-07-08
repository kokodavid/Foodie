package com.koko.foodie.Activities.UploadedRecipes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.koko.foodie.Activities.FoodDetails.FoodDetailPresenter;
import com.koko.foodie.Adapter.IngredientsRecycler;
import com.koko.foodie.Adapter.procedureRecycler;
import com.koko.foodie.Models.TestModelB;
import com.koko.foodie.Models.uploadData;
import com.koko.foodie.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.koko.foodie.Activities.home.HomeActivity.EXTRA_POSITION;

public class FirebaseDetailActivity extends AppCompatActivity {

    FoodDetailPresenter presenter;
    public static final String DEFAULT = "N/A";



    @BindView(R.id.FoodDetailToolbar)
    Toolbar toolbar;

    @BindView(R.id.FoodAppbar)
    AppBarLayout appBarLayout;

    @BindView(R.id.Food_collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.FoodImage)
    ImageView mealThumb;

    @BindView(R.id.serving)
    TextView servingsPeople;
    @BindView(R.id.readyTime)
    TextView servingsTime;

    @BindView(R.id.ingredients)
    TextView ingredientsDetail;

    @BindView(R.id.procedure)
    TextView procedureDetail;

    @BindView(R.id.uploadedBy)
    TextView UploadedBy;


    ArrayList<uploadData> mRecipes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_detail);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String readyIn = intent.getStringExtra("readyIn");
        String serving = intent.getStringExtra("serving");
        String uploadedBy = intent.getStringExtra("uploadedBy");
        String ingredients = intent.getStringExtra("ingredients");
        String procedure = intent.getStringExtra("procedure");

        servingsPeople.setText("Serving(s):" +" "+readyIn);
        servingsTime.setText("Preparation:" +" "+serving + "Mins");
        ingredientsDetail.setText(ingredients);
        procedureDetail.setText(procedure);
        UploadedBy.setText("By:"+ " " + uploadedBy);


        collapsingToolbarLayout.setTitle(name);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.CustomText);


        setupActionBar();


    }

    private void setupActionBar() {
        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.start));
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorWhite));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorWhite));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void setupColorActionBarIcon(Drawable favoriteItemColor) {
        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if ((collapsingToolbarLayout.getHeight() + verticalOffset) < (2 * ViewCompat.getMinimumHeight(collapsingToolbarLayout))) {
                if (toolbar.getNavigationIcon() != null)
                    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);


            } else {
                if (toolbar.getNavigationIcon() != null)
                    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);

            }
        });
    }






}