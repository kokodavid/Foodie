package com.koko.foodie.Api;

import com.koko.foodie.Activities.Cocktails.Cocktail;
import com.koko.foodie.Models.Categories;
import com.koko.foodie.Models.FodieA;
import com.koko.foodie.Models.Food;
import com.koko.foodie.Models.FoodInfo;
import com.koko.foodie.Models.Meals;
import com.koko.foodie.Models.SpoonMeals;
import com.koko.foodie.Models.TestModel;
import com.koko.foodie.Models.TestModelB;
import com.koko.foodie.Models.Wine;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {


    @GET("latest.php")
    Call<Meals> getMeal();

    @GET("search")
    Call<Food> getFood(@Query("") String Food,
                       @Query("apiKey") String apiKey,
                       @Query("number") String number);

    @GET("categories.php")
    Call<Categories> getCategories();

    @GET("filter.php")
    Call<Meals> getMealByCategory(@Query("c") String category);
    
    @GET("search.php")
    Call<Meals> getMealByName(@Query("s") String mealName);

    @GET("popular.php")
    Call<Cocktail> getPopularCocktail();

    @GET("search.php")
    Call<Cocktail> getCocktailByName(@Query("s") String cocktailName);

    @GET("pairing")
    Call<Wine> getWines(@Query("food") String foodPair ,
                        @Query("apiKey") String apiKey);

    @GET("{id}/information")
    Call<TestModelB> getFoodInformation(@Path ("id") int id, @Query("apiKey") String apiKey);
}
