package com.koko.foodie.Activities.WinePairing;

import android.content.Context;

import com.koko.foodie.Models.Meals;
import com.koko.foodie.Models.Wine;

import java.util.List;

public interface WineView {
    void showLoading();
    void hideloading();
    void onErrorLoading(String message);
    void setWine(List<Wine.ProductMatch> wines ,String WineText);

}
