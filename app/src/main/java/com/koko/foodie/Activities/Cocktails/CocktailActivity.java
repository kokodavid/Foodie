package com.koko.foodie.Activities.Cocktails;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.koko.foodie.R;
import com.koko.foodie.Utils.Utils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.koko.foodie.Activities.home.HomeActivity.EXTRA_DETAIL;

public class CocktailActivity extends AppCompatActivity implements CocktailView {

    private AdView mAdView;


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.drinkThumb)
    ImageView drinkThumb;

    @BindView(R.id.category)
    TextView category;


    @BindView(R.id.instructions)
    TextView instructions;

    @BindView(R.id.ingredient)
    TextView ingredients;

    @BindView(R.id.measure)
    TextView measures;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail);
        ButterKnife.bind(this);

        setupActionBar();

        Intent intent = getIntent();
        String cocktailName = intent.getStringExtra(EXTRA_DETAIL);

        //TODO #10 Declare the presenter (put the name of the meal name from the data intent to the presenter)

        CocktailPresenter presenter = new CocktailPresenter(this);
        presenter.getCocktailById(cocktailName);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

//        AdView adView = new AdView(this);
//        adView.setAdSize(AdSize.BANNER);
//        adView.setAdUnitId("ca-app-pub-2470974563764561/6622759966");
//
//        mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
    }

    private void setupActionBar() {
        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.start));
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorWhite));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorWhite));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void setupColorActionBarIcon(Drawable favoriteItemColor) {
        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if ((collapsingToolbarLayout.getHeight() + verticalOffset) < (2 * ViewCompat.getMinimumHeight(collapsingToolbarLayout))) {
                if (toolbar.getNavigationIcon() != null)
                    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.background), PorterDuff.Mode.SRC_ATOP);


            } else {
                if (toolbar.getNavigationIcon() != null)
                    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        MenuItem favoriteItem = menu.findItem(R.id.favorite);
        Drawable favoriteItemColor = favoriteItem.getIcon();
        setupColorActionBarIcon(favoriteItemColor);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setCocktail(Cocktail.Drink cocktail) {
        Picasso.get().load(cocktail.getStrDrinkThumb()).into(drinkThumb);
        collapsingToolbarLayout.setTitle(cocktail.getStrDrink());
        category.setText("Category :" + " " +cocktail.getStrCategory());
        instructions.setText(cocktail.getStrInstructions());
        setupActionBar();

        //===

        if (cocktail.getStrIngredient1() != null) {
            ingredients.append("\n \u2022 " + cocktail.getStrIngredient1());
        }
        if (cocktail.getStrIngredient2() != null) {
            ingredients.append("\n \u2022 " + cocktail.getStrIngredient2());
        }
        if (cocktail.getStrIngredient3() != null) {
            ingredients.append("\n \u2022 " + cocktail.getStrIngredient3());
        }
        if (cocktail.getStrIngredient4()!= null) {
            ingredients.append("\n \u2022 " + cocktail.getStrIngredient4());
        }
        if (cocktail.getStrIngredient5() != null) {
            ingredients.append("\n \u2022 " + cocktail.getStrIngredient5());
        }
        if (cocktail.getStrIngredient6() != null) {
            ingredients.append("\n \u2022 " + cocktail.getStrIngredient6());
        }
        if (cocktail.getStrIngredient7() != null) {
            ingredients.append("\n \u2022 " + cocktail.getStrIngredient7());
        }
        if (cocktail.getStrIngredient8() != null) {
            ingredients.append("\n \u2022 " + cocktail.getStrIngredient8());
        }
        if (cocktail.getStrIngredient9() != null) {
            ingredients.append("\n \u2022 " + cocktail.getStrIngredient9());
        }
        if (cocktail.getStrIngredient10() != null) {
            ingredients.append("\n \u2022 " + cocktail.getStrIngredient10());
        }
        if (cocktail.getStrIngredient11() != null) {
            ingredients.append("\n \u2022 " + cocktail.getStrIngredient11());
        }
        if (cocktail.getStrIngredient12() != null) {
            ingredients.append("\n \u2022 " + cocktail.getStrIngredient12());
        }
        if (cocktail.getStrIngredient13() != null) {
            ingredients.append("\n \u2022 " + cocktail.getStrIngredient13());
        }
        if (cocktail.getStrIngredient14() != null) {
            ingredients.append("\n \u2022 " + cocktail.getStrIngredient14());
        }
        if (cocktail.getStrIngredient15() != null) {
            ingredients.append("\n \u2022 " + cocktail.getStrIngredient15());
        }


        if (cocktail.getStrMeasure1() != null && !Character.isWhitespace(cocktail.getStrMeasure1().charAt(0))) {
            measures.append("\n : " + cocktail.getStrMeasure1());
        }
        if (cocktail.getStrMeasure2() != null && !Character.isWhitespace(cocktail.getStrMeasure2().charAt(0))) {
            measures.append("\n : " + cocktail.getStrMeasure2());
        }
        if (cocktail.getStrMeasure3() != null && !Character.isWhitespace(cocktail.getStrMeasure3().charAt(0))) {
            measures.append("\n : " + cocktail.getStrMeasure3());
        }
        if (cocktail.getStrMeasure4() != null && !Character.isWhitespace(cocktail.getStrMeasure4().charAt(0))) {
            measures.append("\n : " + cocktail.getStrMeasure4());
        }
        if (cocktail.getStrMeasure5() != null && !Character.isWhitespace(cocktail.getStrMeasure5().charAt(0))) {
            measures.append("\n : " + cocktail.getStrMeasure5());
        }
        if (cocktail.getStrMeasure6() != null && !Character.isWhitespace(cocktail.getStrMeasure6().charAt(0))) {
            measures.append("\n : " + cocktail.getStrMeasure6());
        }
        if (cocktail.getStrMeasure7() != null && !Character.isWhitespace(cocktail.getStrMeasure7().charAt(0))) {
            measures.append("\n : " + cocktail.getStrMeasure7());
        }
        if (cocktail.getStrMeasure8() != null && !Character.isWhitespace(cocktail.getStrMeasure8().charAt(0))) {
            measures.append("\n : " + cocktail.getStrMeasure8());
        }
        if (cocktail.getStrMeasure9() != null && !Character.isWhitespace(cocktail.getStrMeasure9().charAt(0))) {
            measures.append("\n : " + cocktail.getStrMeasure9());
        }
        if (cocktail.getStrMeasure10() != null && !Character.isWhitespace(cocktail.getStrMeasure10().charAt(0))) {
            measures.append("\n : " + cocktail.getStrMeasure10());
        }
        if (cocktail.getStrMeasure11() != null && !Character.isWhitespace(cocktail.getStrMeasure11().charAt(0))) {
            measures.append("\n : " + cocktail.getStrMeasure11());
        }
        if (cocktail.getStrMeasure12() != null && !Character.isWhitespace(cocktail.getStrMeasure12().charAt(0))) {
            measures.append("\n : " + cocktail.getStrMeasure12());
        }
        if (cocktail.getStrMeasure13() != null && !Character.isWhitespace(cocktail.getStrMeasure13().charAt(0))) {
            measures.append("\n : " + cocktail.getStrMeasure13());
        }
        if (cocktail.getStrMeasure14() != null && !Character.isWhitespace(cocktail.getStrMeasure14().charAt(0))) {
            measures.append("\n : " + cocktail.getStrMeasure14());
        }
        if (cocktail.getStrMeasure15() != null && !Character.isWhitespace(cocktail.getStrMeasure15().charAt(0))) {
            measures.append("\n : " + cocktail.getStrMeasure15());
        }




    }



    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this,"Error",message);
    }


}