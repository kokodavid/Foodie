package com.koko.foodie.Activities.FoodDetails;


import com.koko.foodie.Models.FoodInfo;

import java.util.List;

public interface FoodDetailView {
    void showLoading();
    void hideloading();
    void onErrorLoading(String message);
    void setFoodInfo(List<FoodInfo> foodName);
}
