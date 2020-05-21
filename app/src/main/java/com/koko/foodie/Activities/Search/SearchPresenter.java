package com.koko.foodie.Activities.Search;

import androidx.annotation.NonNull;

import com.koko.foodie.Models.Meals;
import com.koko.foodie.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter {


    private SearchView view;

    public List<Meals.Meal> meals = new ArrayList<>();


    public SearchPresenter(SearchView view) {
        this.view = view;
    }




    public List<Meals.Meal> getMealsById(String mealName) {
        view.showLoading();

        Utils.getApi().getMealByName(mealName)
                .enqueue(new Callback<Meals>() {
                    @Override
                    public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {
                        view.hideloading();
                        if (response.isSuccessful() && response.body() != null){
                            view.setMeal(response.body().getMeals());
                            meals = response.body().getMeals();



                        }else{
                            view.onErrorLoading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Meals> call,@NonNull Throwable t) {
                        view.hideloading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }
                });

        return meals;
    }
}
