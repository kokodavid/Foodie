package com.koko.foodie.Activities.mealPlanner;

import com.koko.foodie.Models.MealPlan;

import java.util.List;

public interface MealView {
    void showLoading();
    void hideloading();
    void onErrorLoading(String message);
    void setMealPlan(List<MealPlan.Meal> plan,MealPlan.Nutrients nutrients);
}
