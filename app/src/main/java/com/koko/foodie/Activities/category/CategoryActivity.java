package com.koko.foodie.Activities.category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.koko.foodie.Activities.Database.FoodieRoomDB;
import com.koko.foodie.Activities.Favorite.FavoriteDataSource;
import com.koko.foodie.Activities.Favorite.FavoriteRespository;
import com.koko.foodie.Activities.home.HomeActivity;
import com.koko.foodie.Adapter.ViewPagerCategoryAdapter;
import com.koko.foodie.Models.Meals;
import com.koko.foodie.Utils.Common;
import com.koko.foodie.Models.Categories;
import com.koko.foodie.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity {

//    @BindView(R.id.toolBar) Toolbar toolbar;
    @BindView(R.id.tabLayout)
SmartTabLayout tabLayout;
    @BindView(R.id.categoriesDetail) ViewPager categoriesPager;
    List<Meals.Meal> foodlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

//        initActionBar();
        initIntent();
        initDB();
        //TODO 9. Init getIntent() data from home activity

        //TODO 11. Declare fragment viewPager adapter

    }

    private void initIntent() {
        Intent intent = getIntent();
        List<Categories.Category> categories = (List<Categories.Category>) intent.getSerializableExtra(HomeActivity.EXTRA_CATEGORY);
        int position = intent.getIntExtra(HomeActivity.EXTRA_POSITION,0);
        ViewPagerCategoryAdapter adapter = new ViewPagerCategoryAdapter(getSupportFragmentManager(), categories);
        categoriesPager.setAdapter(adapter);
        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.tabLayout);
        viewPagerTab.setViewPager(categoriesPager);
//        tabLayout.setupWithViewPager(categoriesPager);
//        tabLayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.colorWhite)));
//        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorWhite));
        categoriesPager.setCurrentItem(position,true);
        adapter.notifyDataSetChanged();
    }

//    private void initActionBar() {
//        setSupportActionBar(toolbar);
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    public void initDB(){
        Common.foodieRoomDB = FoodieRoomDB.getInstance(this);
        Common.favoriteRespository = FavoriteRespository.getInstance(FavoriteDataSource.getInstance(Common.foodieRoomDB.favoriteDAO()));
        ArrayList<Meals.Meal> filter  = new ArrayList<>();


        }
    }


