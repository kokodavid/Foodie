package com.koko.foodie.Activities.FoodDetails;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
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
import com.koko.foodie.Adapter.StepsAdapter;
import com.koko.foodie.Adapter.TestAdapter;
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
    ViewPager detailPager;
    @BindView(R.id.stepViewpager)
    ViewPager stepViewpager;

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
                favoriteItemColor.mutate().setColorFilter(getResources().getColor(R.color.colorPrimary),
                        PorterDuff.Mode.SRC_ATOP);

            } else {
                if (toolbar.getNavigationIcon() != null)
                    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
                favoriteItemColor.mutate().setColorFilter(getResources().getColor(R.color.colorWhite),
                        PorterDuff.Mode.SRC_ATOP);
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


        TestAdapter testAdapter = new TestAdapter(foodName,instructions, getBaseContext(),image);
        detailPager.setPadding(10, 0, 40, 0);
        detailPager.setAdapter(testAdapter);
        Picasso.get().load(image).into(mealThumb);
        testAdapter.notifyDataSetChanged();

        StepsAdapter stepsAdapter = new StepsAdapter(instructions.get(0).getSteps(), getApplicationContext());
        Log.d("stepsss", String.valueOf(instructions.get(0).getSteps().size()));
        stepViewpager.setPadding(10, 0, 40, 0);
        stepViewpager.setAdapter(stepsAdapter);
        stepsAdapter.notifyDataSetChanged();

    }




}