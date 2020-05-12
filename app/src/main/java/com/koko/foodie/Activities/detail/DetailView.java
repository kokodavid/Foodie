package com.koko.foodie.Activities.detail;

import com.koko.foodie.Models.Meals;

public interface DetailView {
    void showLoading();
    void hideLoading();
    void setMeal(Meals.Meal meal);
    void onErrorLoading(String message);
}
