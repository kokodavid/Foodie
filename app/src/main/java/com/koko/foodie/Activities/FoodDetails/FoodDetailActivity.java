package com.koko.foodie.Activities.FoodDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.koko.foodie.Adapter.CocktailViewPager;
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

    @BindView(R.id.FoodDetailToolbar)
    Toolbar toolbar;

    @BindView(R.id.FoodAppbar)
    AppBarLayout appBarLayout;

    @BindView(R.id.Food_collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.FoodImage)
    ImageView mealThumb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        ButterKnife.bind(this);

        SharedPreferences sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
        String image = sharedPreferences.getString("image",DEFAULT);
        String name = sharedPreferences.getString("name",DEFAULT);

        collapsingToolbarLayout.setTitle(name);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.CustomText);

        Bitmap bitmap = (Bitmap) getIntent().getParcelableExtra("Bitmap");
        mealThumb.setImageBitmap(bitmap);




        Intent intent = getIntent();
        String foodId = intent.getStringExtra(EXTRA_POSITION);

        int id = Integer.parseInt(foodId);

        presenter = new FoodDetailPresenter(this);
        presenter.getFoodDetails(id);




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
    public void setFoodInfo(List<TestModelB.ExtendedIngredient> foodName,List<TestModelB.AnalyzedInstruction> instructions) {

        TestAdapter testAdapter = new TestAdapter(foodName,instructions, this);
        detailPager.setPadding(10, 0, 40, 0);
        detailPager.setAdapter(testAdapter);
        testAdapter.notifyDataSetChanged();
    }

    @Override
    public void setFoodInstructions(List<TestModelB.AnalyzedInstruction> foodInstructions) {

    }


}