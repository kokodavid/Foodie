package com.koko.foodie.Api;

import com.koko.foodie.Activities.Cocktails.Cocktail;
import com.koko.foodie.Models.Categories;
import com.koko.foodie.Models.Meals;

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

    @GET("popular.php")
    Call<Cocktail> getPopularCocktail();

    @GET("search.php")
    Call<Cocktail> getCocktailByName(@Query("s") String cocktailName);



}
