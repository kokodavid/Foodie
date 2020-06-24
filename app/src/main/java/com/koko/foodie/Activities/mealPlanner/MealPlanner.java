package com.koko.foodie.Activities.mealPlanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.koko.foodie.Models.MealPlan;
import com.koko.foodie.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MealPlanner extends AppCompatActivity implements MealView {

    MealPlannerpresenter presenter;
    @BindView(R.id.diet)
    EditText diet;
    @BindView(R.id.calories)
    EditText calories;
    @BindView(R.id.exclude)
    EditText exclude;
    @BindView(R.id.generatedMealsPager)
    ViewPager viewPager;

    String dietValue;
    String numberValue;
    String excludeValue;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_planner);
        ButterKnife.bind(this);

        presenter = new MealPlannerpresenter(this);
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
    public void setMealPlan(List<MealPlan> plan, MealPlan.Nutrients nutrients) {


    }

    public void GenerateMeal(View view) {
        dietValue = diet.getText().toString();
        numberValue = calories.getText().toString();
        int caloriesValue = Integer.valueOf(numberValue);
        excludeValue = exclude.getText().toString();

        presenter.getMealPlanner(caloriesValue,dietValue,excludeValue);
    }
}