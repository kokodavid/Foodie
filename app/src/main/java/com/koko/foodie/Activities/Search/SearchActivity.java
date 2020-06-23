package com.koko.foodie.Activities.Search;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.koko.foodie.Activities.FoodDetails.FoodDetailActivity;
import com.koko.foodie.Activities.detail.DetailActivity;
import com.koko.foodie.Activities.home.HomeActivity;
import com.koko.foodie.Adapter.FoodAdapter;
import com.koko.foodie.Adapter.SearchAdapter;
import com.koko.foodie.Models.Food;
import com.koko.foodie.Models.Meals;
import com.koko.foodie.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.koko.foodie.Activities.home.HomeActivity.EXTRA_DETAIL;
import static com.koko.foodie.Activities.home.HomeActivity.EXTRA_POSITION;


public class SearchActivity extends AppCompatActivity implements SearchView {


    SearchPresenter presenter;
    @BindView(R.id.searchRecycler)
    RecyclerView recyclerView;
    @BindView(R.id.searchView)
    EditText searchView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.searchFoods)
    Button searchFoods;
    public SearchAdapter adapter;
    String name;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);


        searchFoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = searchView.getText().toString();
                presenter.getSearch(name);
            }
        });
        presenter = new SearchPresenter(this);

    }



    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideloading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onErrorLoading(String message) {

    }

    @Override
    public void setSearch(List<Food> foods) {
        FoodAdapter adapter = new FoodAdapter(getBaseContext(),foods);
        recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(),2));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener(new FoodAdapter.ClickListener() {
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



                Intent intent = new Intent(SearchActivity.this, FoodDetailActivity.class);
                intent.putExtra(EXTRA_POSITION,foodId.getText().toString());


                startActivity(intent);

            }
        });
    }



//    @Override
//    public void setMeal(List<Food> food) {
//
//
//        mealList.addAll(meal);
//
//        adapter.setOnItemClickListener((view, position) -> {
//            TextView mealName = view.findViewById(R.id.searchName);
//            Intent intent = new Intent(SearchActivity.this, FoodDetailActivity.class);
//            intent.putExtra(EXTRA_DETAIL, mealName.getText().toString());
//            startActivity(intent);
//        });
//
//    }


}

