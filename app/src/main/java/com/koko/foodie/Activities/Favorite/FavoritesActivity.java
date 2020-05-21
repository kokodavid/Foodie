package com.koko.foodie.Activities.Favorite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.koko.foodie.Activities.Cocktails.CocktailActivity;
import com.koko.foodie.Activities.detail.DetailActivity;
import com.koko.foodie.Activities.home.HomeActivity;
import com.koko.foodie.Adapter.FavoriteAdapter;
import com.koko.foodie.Utils.Common;
import com.koko.foodie.R;
import com.koko.foodie.Utils.RecyclerItemTouchHelper;
import com.koko.foodie.Utils.RecyclerItemTouchHelperListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.koko.foodie.Activities.home.HomeActivity.EXTRA_DETAIL;

public class FavoritesActivity extends AppCompatActivity implements RecyclerItemTouchHelperListener {

    @BindView(R.id.favList) RecyclerView favListRecycler;
    @BindView(R.id.bottomnav)
    BottomNavigationView bm;
    Context context;

    CompositeDisposable compositeDisposable;

    CoordinatorLayout rootLayout;
    FavoriteAdapter favoriteAdapter;
    List<Favorite> localFavorite = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        ButterKnife.bind(this);

        compositeDisposable = new CompositeDisposable();

        favListRecycler.setLayoutManager(new LinearLayoutManager(this));
        favListRecycler.setHasFixedSize(true);

        ItemTouchHelper.SimpleCallback simpleCallback = new RecyclerItemTouchHelper(0,ItemTouchHelper.LEFT,this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(favListRecycler);

        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Swipe Item to the Left to Remove from Favorites", Snackbar.LENGTH_LONG);
        snackbar.show();

        loadFavoritesItem();

        bm=(BottomNavigationView)findViewById(R.id.bottomnav);
        bm.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Intent intent = new Intent(FavoritesActivity.this, HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;

                }
                return true;
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFavoritesItem();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    private void loadFavoritesItem() {
        compositeDisposable.add(Common.favoriteRespository.getFavorites()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Consumer<List<Favorite>>() {
            @Override
            public void accept(List<Favorite> favorites) throws Exception {
                displayFavoriteItems(favorites);
            }
        }));
    }

    private void displayFavoriteItems(List<Favorite> favorites) {
        localFavorite = favorites;
        favoriteAdapter  = new FavoriteAdapter(this,favorites);
        favListRecycler.setAdapter(favoriteAdapter);
        favoriteAdapter.setOnItemClickListener(new FavoriteAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                TextView meal = view.findViewById(R.id.mealName);
                Intent intent = new Intent(FavoritesActivity.this, DetailActivity.class);
                intent.putExtra(EXTRA_DETAIL, meal.getText().toString());
                startActivity(intent);
            }
        });


    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof  FavoriteAdapter.FavoriteViewHolder)
        {
            String name  = localFavorite.get(viewHolder.getAdapterPosition()).name;
            Favorite deletedItem = localFavorite.get(viewHolder.getAdapterPosition());
            int deletedIndex = viewHolder.getAdapterPosition();


            favoriteAdapter.removeItem(deletedIndex);
            Common.favoriteRespository.delete(deletedItem);

            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), new StringBuilder(name).append("Removed From Favorites" + "").toString(),Snackbar.LENGTH_LONG);

            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    favoriteAdapter.restoreItem(deletedItem,deletedIndex);
                    Common.favoriteRespository.insertFav(deletedItem);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }
}
