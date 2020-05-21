package com.koko.foodie.Activities.Search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.koko.foodie.Activities.detail.DetailActivity;
import com.koko.foodie.Adapter.SearchAdapter;
import com.koko.foodie.Models.Meals;
import com.koko.foodie.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.koko.foodie.Activities.home.HomeActivity.EXTRA_DETAIL;


public class SearchActivity extends AppCompatActivity implements SearchView {


    SearchPresenter presenter;
    @BindView(R.id.searchRecycler)
    RecyclerView recyclerView;
    @BindView(R.id.searchView)
    EditText searchView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private SearchAdapter adapter;

    private List<Meals.Meal> mealList;

    SearchAdapter searchAdapter;

    String filter;

    ArrayList<Meals.Meal> filteredNames;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        presenter = new SearchPresenter(this);
        presenter.getMealsById("");



        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });



    }

    private void filter(String toString) {

        //new array list that will hold the filtered data
        filteredNames = new ArrayList<>();

        ArrayList<Meals.Meal> filteredNames = new ArrayList<>();

        for (Meals.Meal meal : mealList) {
            if (meal.getStrMeal().toLowerCase().contains(toString.toLowerCase())) {
                filteredNames.add(meal);
            }

        }


        adapter.filterList(filteredNames);

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
    public void setMeal(List<Meals.Meal> meal) {

        SearchAdapter adapter = new SearchAdapter(meal,getBaseContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(),2));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener(new SearchAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                TextView mealName = view.findViewById(R.id.searchName);
                Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
                intent.putExtra(EXTRA_DETAIL, mealName.getText().toString());
                startActivity(intent);
            }
        });

    }
}

