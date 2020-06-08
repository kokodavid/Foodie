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

    public String API = "6792adb5e9b544dc990c2499f73befb6";


    public static Retrofit getFoodClient() {
        return new Retrofit.Builder().baseUrl(BASE_URL)
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
