package com.koko.foodie.Activities.mealPlanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.koko.foodie.Activities.FoodDetails.FoodDetailActivity;
import com.koko.foodie.Activities.home.HomeActivity;
import com.koko.foodie.Adapter.GeneratedMealsPager;
import com.koko.foodie.Adapter.GeneratedMealsRecycler;
import com.koko.foodie.Models.MealPlan;
import com.koko.foodie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.koko.foodie.Activities.home.HomeActivity.EXTRA_POSITION;

public class MealPlanner extends AppCompatActivity implements MealView, AdapterView.OnItemSelectedListener {

    MealPlannerpresenter presenter;
//    @BindView(R.id.diet)
//    EditText diet;
    @BindView(R.id.calories)
    EditText calories;
    @BindView(R.id.exclude)
    EditText exclude;
    @BindView(R.id.generatedMealsPager)
    RecyclerView viewPager;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;




    String dietValue;
    String numberValue;
    String excludeValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_planner);
        ButterKnife.bind(this);

        presenter = new MealPlannerpresenter(this);
        spinner.setOnItemSelectedListener(this);
        progressBar.setVisibility(View.INVISIBLE);






    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideloading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onErrorLoading(String message) {

    }

    @Override
    public void setMealPlan(List<MealPlan.Meal> plan, MealPlan.Nutrients nutrients) {

        GeneratedMealsRecycler adapter = new GeneratedMealsRecycler(getBaseContext(),plan);
        viewPager.setLayoutManager(new GridLayoutManager(getBaseContext(),1));
        viewPager.setClipToPadding(false);
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();

       adapter.setOnItemClickListener(new GeneratedMealsRecycler.ClickListener() {
           @Override
           public void onClick(View view, int position) {
               TextView foodId = view.findViewById(R.id.id);
               Intent intent = new Intent(MealPlanner.this, FoodDetailActivity.class);
               intent.putExtra(EXTRA_POSITION,foodId.getText().toString());
               startActivity(intent);

               TextView foodName = view.findViewById(R.id.mealName);
               TextView servingPeople = view.findViewById(R.id.servings);

               SharedPreferences sharedPref = getSharedPreferences("MyData",MODE_PRIVATE);
               SharedPreferences.Editor editor = sharedPref.edit();
               editor.putString("name",foodName.getText().toString());
               editor.putString("servingTime",servingPeople.getText().toString());
               editor.commit();
           }
       });




    }

    public void GenerateMeal(View view) {
//        dietValue = diet.getText().toString();
        numberValue = calories.getText().toString();
        int caloriesValue = Integer.valueOf(numberValue);
        excludeValue = exclude.getText().toString();
        progressBar.setVisibility(View.VISIBLE);
        presenter.getMealPlanner(caloriesValue,dietValue,excludeValue);

        if (numberValue.matches("")){
            Toast.makeText(this, "You did not enter a username", Toast.LENGTH_SHORT).show();
            return;
        }

        if (excludeValue.matches("")){
            Toast.makeText(this, "You did not enter a username", Toast.LENGTH_SHORT).show();
            return;
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        dietValue = parent.getSelectedItem().toString();
        ((TextView) view).setTextColor(getResources().getColor(R.color.textColor));

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void regenerate(View view) {
        numberValue = calories.getText().toString();
        int caloriesValue = Integer.valueOf(numberValue);
        excludeValue = exclude.getText().toString();
        presenter.getMealPlanner(caloriesValue,dietValue,excludeValue);
        progressBar.setVisibility(View.VISIBLE);

        if(TextUtils.isEmpty(numberValue)){
            calories.setError("Input Number of Calories");
            return;
        }
        if(TextUtils.isEmpty(excludeValue)){
            calories.setError("What would you Like to Exclude ?");
            return;
        }


    }
}