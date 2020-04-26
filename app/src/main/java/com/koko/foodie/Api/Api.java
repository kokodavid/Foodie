package com.koko.foodie.Api;

import com.koko.foodie.Models.Categories;
import com.koko.foodie.Models.Meals;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {


    @GET("search.php?f=a")
    Call<Meals> getMeal();


    @GET("categories.php")
    Call<Categories> getCategories();
}
