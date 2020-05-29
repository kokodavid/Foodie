package com.koko.foodie.Activities.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.koko.foodie.Activities.Cocktails.Cocktail;
import com.koko.foodie.Activities.Cocktails.CocktailActivity;
import com.koko.foodie.Activities.Database.FoodieRoomDB;
import com.koko.foodie.Activities.Favorite.FavoriteDataSource;
import com.koko.foodie.Activities.Favorite.FavoriteRespository;
import com.koko.foodie.Activities.Favorite.FavoritesActivity;
import com.koko.foodie.Activities.Search.SearchActivity;
import com.koko.foodie.Activities.category.CategoryActivity;
import com.koko.foodie.Activities.detail.DetailActivity;
import com.koko.foodie.Adapter.CocktailViewPager;
import com.koko.foodie.Adapter.LatestViewPager;
import com.koko.foodie.Adapter.ViewPagerAdapter;
import com.koko.foodie.Utils.Common;
import com.koko.foodie.Models.Categories;
import com.koko.foodie.Models.Meals;
import com.koko.foodie.R;
import com.koko.foodie.Utils.Utils;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
//    @BindView(R.id.searchView)
//    CardView search;

    private ImageView errorImage;
    private TextView errorTitle, errorMessage;
    private Button btnRetry;
    private RelativeLayout errorLayout;
    private AdView mAdView;




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

        errorLayout = findViewById(R.id.errorLayout);
        errorImage = findViewById(R.id.errorImage);
        errorTitle = findViewById(R.id.errorTitle);
        errorMessage = findViewById(R.id.errorMessage);
        btnRetry = findViewById(R.id.btnRetry);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        initDB();

//        FavoriteList.setOnClickListener(v -> {
//            Intent intent = new Intent(HomeActivity.this, FavoritesActivity.class);
//            startActivity(intent);
//        });


//        AdView adView = new AdView(this);
//        adView.setAdSize(AdSize.BANNER);
//        adView.setAdUnitId("ca-app-pub-2470974563764561/9347967981");
//
//        mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
//                startActivity(intent);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//            }
//        });

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
                    }
                return true;
            }
        });


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
        latestRecyclerView.setPadding(10, 0, 360, 0);
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
        viewPagerCategory.setPadding(10, 0, 380, 0);
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
        latestCocktail.setPadding(10, 0, 360, 0);
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
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this, "Title", message);
    }

    @Override
    public void setMeal(List<Meals.Meal> meal) {

    }


    public void initDB(){
        Common.foodieRoomDB = FoodieRoomDB.getInstance(this);
        Common.favoriteRespository = FavoriteRespository.getInstance(FavoriteDataSource.getInstance(Common.foodieRoomDB.favoriteDAO()));
    }

    private boolean loadMyFragment(Fragment fm) {

        return false;
    }



}