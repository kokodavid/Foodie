package com.koko.foodie.Activities.home;

import androidx.annotation.NonNull;

import com.koko.foodie.Activities.Cocktails.Cocktail;
import com.koko.foodie.Models.Categories;
import com.koko.foodie.Models.Meals;
import com.koko.foodie.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter extends HomeActivity {

    private HomeView view;

    public List<Meals.Meal> meals = new ArrayList<>();

    public HomePresenter(HomeView view) {
        this.view = view;
    }

    public List<Meals.Meal> getMeals() {

        view.showLoading();
       Call<Meals> mealsCall = Utils.getApi().getMeal();
        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {
                    view.hideloading();
                if (response.isSuccessful() && response.body() != null) {

                    view.setMeals(response.body().getMeals());
                    meals = response.body().getMeals();

                }
                else {
                    // TODO 23 Show an error message if the conditions are not met
                    view.onErrorLoading(response.message());

                }
            }



            @Override
            public void onFailure(@NonNull Call<Meals> call,@NonNull Throwable t) {
                view.hideloading();
                view.onErrorLoading(t.getLocalizedMessage());


            }
        });
    return meals;
    }


    void getCategories() {

        view.showLoading();


        Call<Categories> categoriesCall = Utils.getApi().getCategories();
        categoriesCall.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(@NonNull Call<Categories> call,@NonNull Response<Categories> response) {
                view.hideloading();
                if (response.isSuccessful() && response.body() != null) {
                    /*
                     * TODO 30 Receive the result
                     * input the results obtained into the setMeals() behavior
                     * and enter response.body() to the parameter
                     */
                    view.setCategory(response.body().getCategories());


                }
                else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Categories> call,@NonNull Throwable t) {

                view.hideloading();
                t.getLocalizedMessage();

                // TODO 32 Close loading
                // TODO 33 Show an error message
            }
        });
    }

    void getCocktails(){
        view.showLoading();
        Call<Cocktail> cocktailCall = Utils.getCocktailApi().getPopularCocktail();
        cocktailCall.enqueue(new Callback<Cocktail>() {
            @Override
            public void onResponse(@NonNull Call<Cocktail> call,@NonNull Response<Cocktail> response) {
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

//    public void getMealById(String mealName){

        public List<Meals.Meal> getMealsById(String mealName) {
        view.showLoading();

        Utils.getApi().getMealByName("")
                .enqueue(new Callback<Meals>() {
                    @Override
                    public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {
                        view.hideloading();
                        if (response.isSuccessful() && response.body() != null){
                            meals = response.body().getMeals();

                        }else{
                            view.onErrorLoading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Meals> call,@NonNull Throwable t) {
                        view.hideloading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }
                });

            return meals;
        }

    }




