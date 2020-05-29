package com.koko.foodie.Activities.Search;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.koko.foodie.Activities.detail.DetailActivity;
import com.koko.foodie.Adapter.SearchAdapter;
import com.koko.foodie.Models.Meals;
import com.koko.foodie.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public SearchAdapter adapter;

    public List<Meals.Meal> mealList = new ArrayList<>();







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        presenter = new SearchPresenter(this);
        presenter.getMealsById("");

        adapter = new SearchAdapter(mealList, getApplicationContext());

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("Haiyaaa", ""+s);

                filter(s.toString());
            }
        });



    }

    private void filter(String text) {

        ArrayList<Meals.Meal> filterList = new ArrayList<>();

       Log.e("YEZZZZ", ""+mealList.size());

        for (Meals.Meal item: mealList){
            if (item.getStrMeal().toLowerCase().contains(text)){

                filterList.add(item);

            }
        }
        adapter.notifyDataSetChanged();
        adapter.filteredList(filterList);
//        Toast.makeText(this, filterList.get(0).getStrMeal(), Toast.LENGTH_SHORT).show();
//        Log.d("Alafuu", ""+ filterList.get(0).getStrMeal());
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
        mealList.addAll(meal);

        adapter.setOnItemClickListener((view, position) -> {
            TextView mealName = view.findViewById(R.id.searchName);
            Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
            intent.putExtra(EXTRA_DETAIL, mealName.getText().toString());
            startActivity(intent);
        });

    }


}

