package com.koko.foodie.Activities.Search;

import androidx.annotation.NonNull;

import com.koko.foodie.Models.Food;
import com.koko.foodie.Models.Meals;
import com.koko.foodie.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter {


    private SearchView view;



    public SearchPresenter(SearchView view) {
        this.view = view;
    }

    void getSearch(String name){

        Call<Food> foodCall = Utils.getSearchResults().getSearchedFood(name,"6792adb5e9b544dc990c2499f73befb6","500");
        foodCall.enqueue(new Callback<Food>() {
            @Override
            public void onResponse(@NonNull Call<Food> call,@NonNull Response<Food> response) {
                if (response.isSuccessful() && response.body() != null){
                    view.setSearch(response.body().getResults());
                }else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Food> call,@NonNull Throwable t) {
                view.showLoading();
                t.getLocalizedMessage();
            }
        });


    }



}
