package com.koko.foodie.Activities.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.koko.foodie.Activities.Cocktails.Cocktail;
import com.koko.foodie.Activities.Cocktails.CocktailActivity;
import com.koko.foodie.Activities.Database.FoodieRoomDB;
import com.koko.foodie.Activities.Explore.ExploreActivity;
import com.koko.foodie.Activities.Favorite.FavoriteDataSource;
import com.koko.foodie.Activities.Favorite.FavoriteRespository;
import com.koko.foodie.Activities.Favorite.FavoritesActivity;
import com.koko.foodie.Activities.FoodDetails.FoodDetailActivity;
import com.koko.foodie.Activities.Search.SearchActivity;
import com.koko.foodie.Activities.UploadRecipeActivity;
import com.koko.foodie.Activities.category.CategoryActivity;
import com.koko.foodie.Activities.detail.DetailActivity;
import com.koko.foodie.Adapter.CocktailViewPager;
import com.koko.foodie.Adapter.FoodAdapter;
import com.koko.foodie.Adapter.LatestViewPager;

import com.koko.foodie.Adapter.UserRecipeAdapter;
import com.koko.foodie.Adapter.ViewPagerAdapter;
import com.koko.foodie.Models.Food;
import com.koko.foodie.Models.TestModelB;
import com.koko.foodie.Models.uploadData;
import com.koko.foodie.Utils.Common;
import com.koko.foodie.Models.Categories;
import com.koko.foodie.Models.Meals;
import com.koko.foodie.R;
import com.koko.foodie.Utils.Utils;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.hoang8f.android.segmented.SegmentedGroup;

// TODO 31 implement the HomeView interface at the end
public class HomeActivity extends AppCompatActivity implements HomeView {


    @BindView(R.id.viewPagerHeader)
    ViewPager viewPagerCategory;
    @BindView(R.id.bottomnav)
    BottomNavigationView bm;
    @BindView(R.id.recyclerLatest)
    ViewPager latestRecyclerView;
    @BindView(R.id.cocktailLatest)
    ViewPager latestCocktail;
    @BindView(R.id.segmented2)
    SegmentedGroup segmentedGroup;
    @BindView(R.id.button2)
    RadioButton radioButton;

    @BindView(R.id.rootView)
    RelativeLayout rootView;

    @BindView(R.id.spoonRecycler)
    RecyclerView spoonRecycler;

    @BindView(R.id.searchPage)
    ImageView search;


    List<TestModelB>list;

    HomePresenter presenter;

    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_DETAIL = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        ButterKnife.bind(this);

        presenter = new HomePresenter(this);
        presenter.getCategories();
        presenter.getMeals();
        presenter.getCocktails();
        presenter.getAllFood();


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addRecipe = new Intent(HomeActivity.this, SearchActivity.class);
                addRecipe.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(addRecipe);
            }
        });



        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        initDB();

        bm.setItemRippleColor(ColorStateList.valueOf(getResources().getColor(R.color.start)));
        bm=(BottomNavigationView)findViewById(R.id.bottomnav);
        bm.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.favorites:
                        Intent favorites = new Intent(HomeActivity.this, FavoritesActivity.class);
                        favorites.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(favorites);
                        break;

                    case R.id.add_recipe:
                        Intent addRecipe = new Intent(HomeActivity.this, UploadRecipeActivity.class);
                        addRecipe.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(addRecipe);
                        break;
                    }
                return true;
            }
        });


        SegmentedGroup segmented2 = (SegmentedGroup) rootView.findViewById(R.id.segmented2);
        segmented2.setTintColor(getResources().getColor(R.color.fade));


    }

    @Override
    public void showLoading() {

        findViewById(R.id.shimmerMeal).setVisibility(View.VISIBLE);
        findViewById(R.id.shimmerCategory).setVisibility(View.VISIBLE);
        findViewById(R.id.shimmerMeal2).setVisibility(View.VISIBLE);

    }

    @Override
    public void hideloading() {
        findViewById(R.id.shimmerMeal).setVisibility(View.GONE);
        findViewById(R.id.shimmerCategory).setVisibility(View.GONE);
        findViewById(R.id.shimmerMeal2).setVisibility(View.GONE);

    }

    @Override
    public void setMeals(List<Meals.Meal> meal) {
        LatestViewPager viewPager = new LatestViewPager(meal, this);
        latestRecyclerView.setAdapter(viewPager);
        latestRecyclerView.setPadding(10, 0, 400, 0);
        viewPager.notifyDataSetChanged();

        viewPager.setOnItemClickListener(new LatestViewPager.ClickListener() {
            @Override
            public void onClick(View v, int position) {
                TextView meal = v.findViewById(R.id.mealName);
                Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
                intent.putExtra(EXTRA_DETAIL, meal.getText().toString());
                startActivity(intent);
            }
        });


    }

    @Override
    public void setCategory(List<Categories.Category> category) {
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(category, this);
        viewPagerCategory.setAdapter(pagerAdapter);
        viewPagerCategory.setPadding(10, 0, 500, 0);
        pagerAdapter.notifyDataSetChanged();

        pagerAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(this, CategoryActivity.class);
            //TODO 8. add extra data (put to intent)
            intent.putExtra(EXTRA_CATEGORY, (Serializable) category);
            intent.putExtra(EXTRA_POSITION, position);
            startActivity(intent);
        });
    }

    @Override
    public void setCocktail(List<Cocktail.Drink> cocktail) {
        CocktailViewPager CocktailPager = new CocktailViewPager(cocktail, this);
        latestCocktail.setAdapter(CocktailPager);
        latestCocktail.setPadding(10, 0, 400, 0);
        CocktailPager.notifyDataSetChanged();


        CocktailPager.setOnItemClickListener(new CocktailViewPager.ClickListener() {
            @Override
            public void onClick(View v, int position) {
                TextView meal = v.findViewById(R.id.cocktailName);
                Intent intent = new Intent(HomeActivity.this, CocktailActivity.class);
                intent.putExtra(EXTRA_DETAIL, meal.getText().toString());
                startActivity(intent);
            }
        });
    }

    @Override
    public void setFood(List<Food> food) {
        FoodAdapter adapter = new FoodAdapter(getBaseContext(),food);
        spoonRecycler.setLayoutManager(new GridLayoutManager(getBaseContext(),2));
        spoonRecycler.setClipToPadding(false);
        spoonRecycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener(new FoodAdapter.ClickListener(){
            @Override
            public void onClick(View view, int position) {
                TextView foodId = view.findViewById(R.id.id);
                TextView foodName = view.findViewById(R.id.mealName);
                TextView servingTime = view.findViewById(R.id.readyIn);
                TextView servingPeople = view.findViewById(R.id.servings);

                SharedPreferences sharedPref = getSharedPreferences("MyData",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("name",foodName.getText().toString());
                editor.putString("servingPeople",servingTime.getText().toString());
                editor.putString("servingTime",servingPeople.getText().toString());
                editor.commit();



                Intent intent = new Intent(HomeActivity.this, FoodDetailActivity.class);
                intent.putExtra(EXTRA_POSITION,foodId.getText().toString());


                startActivity(intent);


            }
        });



    }



    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this, "Title", message);
    }


    public void initDB(){
        Common.foodieRoomDB = FoodieRoomDB.getInstance(this);
        Common.favoriteRespository = FavoriteRespository.getInstance(FavoriteDataSource.getInstance(Common.foodieRoomDB.favoriteDAO()));
    }

    private boolean loadMyFragment(Fragment fm) {

        return false;
    }


    public void Explore(View view) {
        Intent addRecipe = new Intent(HomeActivity.this, ExploreActivity.class);
        addRecipe.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(addRecipe);
    }


}