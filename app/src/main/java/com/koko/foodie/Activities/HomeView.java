package com.koko.foodie.Activities;

import com.koko.foodie.Models.Categories;
import com.koko.foodie.Models.Meals;

import java.util.List;

public interface HomeView {

    void showLoading();
    void hideloading();
    void setMeals(List<Meals.Meal>meal);
    void setCategory(List<Categories.Category>category);
    void onErrorLoading(String message);
}
