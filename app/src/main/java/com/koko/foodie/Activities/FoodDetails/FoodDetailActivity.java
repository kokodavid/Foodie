package com.koko.foodie.Activities.FoodDetails;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.koko.foodie.Adapter.CocktailViewPager;
import com.koko.foodie.Adapter.IngredientsRecycler;
import com.koko.foodie.Adapter.StepsAdapter;
import com.koko.foodie.Adapter.TestAdapter;
import com.koko.foodie.Adapter.procedureRecycler;
import com.koko.foodie.Models.FodieA;
import com.koko.foodie.Models.Food;
import com.koko.foodie.Models.SpoonMeals;
import com.koko.foodie.Models.TestModel;
import com.koko.foodie.Models.TestModelB;
import com.koko.foodie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.koko.foodie.Activities.home.HomeActivity.EXTRA_DETAIL;
import static com.koko.foodie.Activities.home.HomeActivity.EXTRA_POSITION;

public class FoodDetailActivity extends AppCompatActivity implements FoodDetailView{

    FoodDetailPresenter presenter;
    public static final String DEFAULT = "N/A";


    @BindView(R.id.detailViewPager)
    RecyclerView detailPager;
    @BindView(R.id.stepViewpager)
    RecyclerView stepViewpager;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        ButterKnife.bind(this);

        SharedPreferences sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);

        String name = sharedPreferences.getString("name",DEFAULT);
        String servingTime = sharedPreferences.getString("servingTime",DEFAULT);
        String servingPeople = sharedPreferences.getString("servingPeople",DEFAULT);

        collapsingToolbarLayout.setTitle(name);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.CustomText);

        servingsPeople.setText("Preparation :"+ " "+ servingPeople  + " " + "Minuets" + "     " + "||");
        servingsTime.setText( "Serving(s) :" +" "+ servingTime);




        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra("imageUrl");
        String foodId = intent.getStringExtra(EXTRA_POSITION);

        Picasso.get().load("https://spoonacular.com/recipeImages/" + imageUrl).into(mealThumb);

        int id = Integer.parseInt(foodId);

        presenter = new FoodDetailPresenter(this);
        presenter.getFoodDetails(id);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideloading() {

    }

    @Override
    public void onErrorLoading(String message) {

    }



    @Override
    public void setFoodInfo(List<TestModelB.ExtendedIngredient> foodName,List<TestModelB.AnalyzedInstruction> instructions,String image) {


        IngredientsRecycler testAdapter = new IngredientsRecycler(foodName,instructions, getBaseContext(),image);
        detailPager.setLayoutManager(new GridLayoutManager(getBaseContext(),1));
        detailPager.setClipToPadding(false);
        detailPager.setAdapter(testAdapter);
        testAdapter.notifyDataSetChanged();
        Picasso.get().load(image).into(mealThumb);


        procedureRecycler stepsAdapter = new procedureRecycler(instructions.get(0).getSteps(), getApplicationContext());
        stepViewpager.setLayoutManager(new GridLayoutManager(getBaseContext(),1));
        stepViewpager.setClipToPadding(false);
        stepViewpager.setAdapter(stepsAdapter);
        testAdapter.notifyDataSetChanged();

    }




}