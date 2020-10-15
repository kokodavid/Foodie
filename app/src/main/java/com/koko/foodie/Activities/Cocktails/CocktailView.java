package com.koko.foodie.Activities.Cocktails;

public interface CocktailView {
    void showLoading();
    void hideLoading();
    void setCocktail(Cocktail.Drink cocktail);
    void onErrorLoading(String message);
}
