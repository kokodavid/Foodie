package com.koko.foodie.Activities.Search;

import com.koko.foodie.Models.Meals;

import java.util.List;

public interface SearchView {

    void showLoading();
    void hideloading();
    void onErrorLoading(String message);
    void setMeal(List<Meals.Meal> meal);


}
