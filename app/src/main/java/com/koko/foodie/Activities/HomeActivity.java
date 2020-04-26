package com.koko.foodie.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.RelativeLayout;

import com.koko.foodie.Adapter.RecyclerViewHomeAdapter;
import com.koko.foodie.Adapter.ViewPagerAdapter;
import com.koko.foodie.Models.Categories;
import com.koko.foodie.Models.Meals;
import com.koko.foodie.R;
import com.koko.foodie.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

// TODO 31 implement the HomeView interface at the end
public class HomeActivity extends AppCompatActivity implements HomeView {

    @BindView(R.id.viewPagerHeader) ViewPager viewPagerCategory;
    @BindView(R.id.recyclerLatest)  RecyclerView latestRecyclerView;

    HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        presenter = new HomePresenter(this);
        presenter.getCategories();
        presenter.getMeals();
    }

    @Override
    public void showLoading() {

        findViewById(R.id.shimmerMeal).setVisibility(View.VISIBLE);
        findViewById(R.id.shimmerCategory).setVisibility(View.VISIBLE);

    }

    @Override
    public void hideloading() {
        findViewById(R.id.shimmerMeal).setVisibility(View.GONE);
        findViewById(R.id.shimmerCategory).setVisibility(View.GONE);

    }

    @Override
    public void setMeals(List<Meals.Meal> meal) {
        RecyclerViewHomeAdapter homeAdapter = new RecyclerViewHomeAdapter(meal , this);
        latestRecyclerView.setAdapter(homeAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        latestRecyclerView.setLayoutManager(layoutManager);
        latestRecyclerView.setClipToPadding(false);
        latestRecyclerView.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public void setCategory(List<Categories.Category> category) {
        ViewPagerAdapter pagerAdapter  = new ViewPagerAdapter(category, this);
        viewPagerCategory.setAdapter(pagerAdapter);
        viewPagerCategory.setPadding(20,0,150,0);
        pagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this,"Title",message);
    }

    // TODO 36 Overriding the interface

}