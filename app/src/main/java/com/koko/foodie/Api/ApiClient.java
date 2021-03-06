package com.koko.foodie.Api;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v2/9973533/";

    private static final String COCKTAIL_BASE_URL = "https://www.thecocktaildb.com/api/json/v2/9973533/";

    private static final String BEER_BASE_URL = "https://api.punkapi.com/v2/";

    private static final String WINE_PAIRING = "https://api.spoonacular.com/food/wine/";

    private static final String MEAL_GENERATOR = "https://api.spoonacular.com/mealplanner/";

    private static final String SPOON_MEALS = "https://api.spoonacular.com/recipes/";



    private static final String SPOON_MEALS_DETAILS = "https://api.spoonacular.com/recipes/";

    private static final String SEARCH = "https://api.spoonacular.com/recipes/";

    public static Retrofit getFoodSearch() {
        return new Retrofit.Builder().baseUrl(SEARCH)
                .client(provideOkHttp())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getMealPlanner() {
        return new Retrofit.Builder().baseUrl(MEAL_GENERATOR)
                .client(provideOkHttp())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getFoodDetailClient() {
        return new Retrofit.Builder().baseUrl(SPOON_MEALS_DETAILS)
                .client(provideOkHttp())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getFoodClient() {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .client(provideOkHttp())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getSpoonClient() {
        return new Retrofit.Builder().baseUrl(SPOON_MEALS)
                .client(provideOkHttp())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getWineClient() {
        return new Retrofit.Builder().baseUrl(WINE_PAIRING)
                .client(provideOkHttp())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getCocktailClient() {
        return new Retrofit.Builder().baseUrl(COCKTAIL_BASE_URL)
                .client(provideOkHttp())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getBeerClient() {
        return new Retrofit.Builder().baseUrl(BEER_BASE_URL)
                .client(provideOkHttp())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }



    private static Interceptor provideLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    private static OkHttpClient provideOkHttp() {
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addNetworkInterceptor(provideLoggingInterceptor())
                .build();
    }



}
