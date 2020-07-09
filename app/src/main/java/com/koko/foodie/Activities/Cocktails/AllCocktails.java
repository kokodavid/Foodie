package com.koko.foodie.Activities.Cocktails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.koko.foodie.Activities.home.HomeActivity;
import com.koko.foodie.Adapter.CocktailRecycler;
import com.koko.foodie.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.koko.foodie.Activities.home.HomeActivity.EXTRA_DETAIL;

public class AllCocktails extends AppCompatActivity implements AllCocktailsView {

    AllCocktailsPresenter presenter;
    @BindView(R.id.cocktailsRecycler)
    RecyclerView cocktailRecycler;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cocktails);
        ButterKnife.bind(this);

        presenter = new AllCocktailsPresenter(this);
        presenter.getCocktails();
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
    public void setCocktail(List<Cocktail.Drink> cocktail) {
        CocktailRecycler recycler = new CocktailRecycler(getBaseContext(),cocktail);
        cocktailRecycler.setLayoutManager(new GridLayoutManager(getBaseContext(),1));
        cocktailRecycler.setClipToPadding(false);
        cocktailRecycler.setAdapter(recycler);
        recycler.notifyDataSetChanged();

        recycler.setOnItemClickListener(new CocktailRecycler.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                TextView meal = view.findViewById(R.id.cocktailName);
                Intent intent = new Intent(AllCocktails.this, CocktailActivity.class);
                intent.putExtra(EXTRA_DETAIL, meal.getText().toString());
                startActivity(intent);
            }
        });

    }

    @Override
    public void onErrorLoading(String message) {

    }
}