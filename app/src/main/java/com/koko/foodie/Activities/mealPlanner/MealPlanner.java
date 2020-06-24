package com.koko.foodie.Activities.mealPlanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.koko.foodie.Activities.FoodDetails.FoodDetailActivity;
import com.koko.foodie.Activities.home.HomeActivity;
import com.koko.foodie.Adapter.GeneratedMealsPager;
import com.koko.foodie.Models.MealPlan;
import com.koko.foodie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.koko.foodie.Activities.home.HomeActivity.EXTRA_POSITION;

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
    public void setMealPlan(List<MealPlan.Meal> plan, MealPlan.Nutrients nutrients) {
        GeneratedMealsPager adapter = new GeneratedMealsPager(plan,getBaseContext());
        viewPager.setPadding(10, 0, 40, 0);
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setOnClickListener(new GeneratedMealsPager.ClickListener() {
            @Override
            public void onClick(View v, int position) {
                TextView foodId = v.findViewById(R.id.id);
                Intent intent = new Intent(MealPlanner.this, FoodDetailActivity.class);
                intent.putExtra(EXTRA_POSITION,foodId.getText().toString());
                startActivity(intent);

                TextView foodName = v.findViewById(R.id.mealName);
                TextView servingTime = v.findViewById(R.id.readyIn);
                TextView servingPeople = v.findViewById(R.id.servings);

                SharedPreferences sharedPref = getSharedPreferences("MyData",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("name",foodName.getText().toString());
                editor.putString("servingPeople",servingTime.getText().toString());
                editor.putString("servingTime",servingPeople.getText().toString());
                editor.commit();

            }
        });


    }

    public void GenerateMeal(View view) {
        dietValue = diet.getText().toString();
        numberValue = calories.getText().toString();
        int caloriesValue = Integer.valueOf(numberValue);
        excludeValue = exclude.getText().toString();

        presenter.getMealPlanner(caloriesValue,dietValue,excludeValue);
    }
}