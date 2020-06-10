package com.koko.foodie.Activities.WinePairing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.koko.foodie.Activities.MainActivity;
import com.koko.foodie.Activities.home.HomePresenter;
import com.koko.foodie.Adapter.RecyclerViewMealByCategory;
import com.koko.foodie.Adapter.SearchAdapter;
import com.koko.foodie.Adapter.WinePairingAdapter;
import com.koko.foodie.Models.Wine;
import com.koko.foodie.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WinePairingActivity extends AppCompatActivity implements WineView {

    WinePresenter presenter;

    @BindView(R.id.searchWine)
    EditText searchInput;
    @BindView(R.id.searchButton)
    Button searchButton;

     String value;
    @BindView(R.id.WinesRecycler)
    RecyclerView winesRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_pairing);
        ButterKnife.bind(this);


        presenter = new WinePresenter(this);

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
    public void setWine(List<Wine.ProductMatch> wines,String WineText) {
        WinePairingAdapter adapter = new WinePairingAdapter(wines,getBaseContext(), WineText);
        winesRecycler.setLayoutManager(new GridLayoutManager(getBaseContext(),1));
        winesRecycler.setClipToPadding(false);
        winesRecycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }






    public void searchWines(View view) {
        value = searchInput.getText().toString();
        presenter.getWines(value);
    }

}