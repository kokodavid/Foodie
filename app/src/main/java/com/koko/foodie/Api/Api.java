package com.koko.foodie.Api;

import com.koko.foodie.Activities.Cocktails.Cocktail;
import com.koko.foodie.Models.Categories;
import com.koko.foodie.Models.Meals;
import com.koko.foodie.Models.Wine;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {


    @GET("latest.php")
    Call<Meals> getMeal();


    @GET("categories.php")
    Call<Categories> getCategories();

    @GET("filter.php")
    Call<Meals> getMealByCategory(@Query("c") String category);
    
    @GET("search.php")
    Call<Meals> getMealByName(@Query("s") String mealName);

    @GET("search.php")
    Call<Cocktail> getPopularCocktail(@Query("s") String cocktailName);

    @GET("search.php")
    Call<Cocktail> getCocktailByName(@Query("s") String cocktailName);

    @GET("pairing")
    Call<Wine> getWines(@Query("food") String foodPair , @Query("apiKey") String apiKey);
}
