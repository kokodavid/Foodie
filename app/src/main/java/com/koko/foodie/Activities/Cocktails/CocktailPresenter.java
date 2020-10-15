package com.koko.foodie.Activities.Cocktails;

import androidx.annotation.NonNull;

import com.koko.foodie.Utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CocktailPresenter {
    private CocktailView view;
    public CocktailPresenter(CocktailView view){
        this.view = view;
    }

    void getCocktailById(String cocktailName){
        view.showLoading();

        Utils.getCocktailApi().getCocktailByName(cocktailName)
                .enqueue(new Callback<Cocktail>() {
                    @Override
                    public void onResponse(@NonNull Call<Cocktail> call,@NonNull Response<Cocktail> response) {
                        view.hideLoading();
                        if (response.isSuccessful() && response.body() != null){
                            view.setCocktail(response.body().getDrinks().get(0));
                        }else{
                            view.onErrorLoading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Cocktail> call,@NonNull Throwable t) {
                            view.hideLoading();
                            view.onErrorLoading(t.getLocalizedMessage());
                    }
                });
    }
}
