package com.koko.foodie.Activities.Cocktails;

import androidx.annotation.NonNull;

import com.koko.foodie.Activities.home.HomeView;
import com.koko.foodie.Utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllCocktailsPresenter extends AllCocktails {

    private AllCocktailsView view;
    public AllCocktailsPresenter(AllCocktailsView view) {
        this.view = view;
    }


    void getCocktails(){
        view.showLoading();
        Call<Cocktail> cocktailCall = Utils.getCocktailApi().getCocktailByName("");
        cocktailCall.enqueue(new Callback<Cocktail>() {
            @Override
            public void onResponse(@NonNull Call<Cocktail> call, @NonNull Response<Cocktail> response) {
                view.hideloading();
                if (response.isSuccessful() && response.body() != null){
                    view.setCocktail(response.body().getDrinks());
                }else{
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Cocktail> call,@NonNull Throwable t) {
                view.showLoading();
                t.getLocalizedMessage();




            }
        });
    }
}
