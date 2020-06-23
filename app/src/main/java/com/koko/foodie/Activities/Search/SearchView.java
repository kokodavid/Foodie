package com.koko.foodie.Activities.Search;

import com.koko.foodie.Models.Food;
import com.koko.foodie.Models.Meals;
import com.koko.foodie.Models.Wine;

import java.util.List;

public interface SearchView {

    void showLoading();
    void hideloading();
    void onErrorLoading(String message);
    void setSearch(List<Food> foods);


}
