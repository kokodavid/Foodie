package com.koko.foodie.Activities.home;

import com.google.firebase.database.DataSnapshot;
import com.koko.foodie.Activities.Cocktails.Cocktail;
import com.koko.foodie.Models.Categories;
import com.koko.foodie.Models.Food;
import com.koko.foodie.Models.Meals;
import com.koko.foodie.Models.uploadData;

import java.util.List;

public interface HomeView {

    void showLoading();
    void hideloading();
    void setMeals(List<Meals.Meal>meal);
    void setCategory(List<Categories.Category>category);
    void setCocktail(List<Cocktail.Drink>cocktail);
    void onErrorLoading(String message);
    void setFood(List<Food> food);
    void setFirebaseData(Iterable<DataSnapshot> children);
}
