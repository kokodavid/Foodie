package com.koko.foodie.Activities.Cocktails;

import java.util.List;

public interface AllCocktailsView {
    void showLoading();
    void hideloading();
    void setCocktail(List<Cocktail.Drink> cocktail);
    void onErrorLoading(String message);


}
