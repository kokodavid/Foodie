package com.koko.foodie.Utils;

import android.app.AlertDialog;
import android.content.Context;

import com.koko.foodie.Api.Api;
import com.koko.foodie.Api.ApiClient;


public class Utils {

    public static Api getApi() {
        return ApiClient.getFoodClient().create(Api.class);
    }

        public static Api getCocktailApi() {
            return ApiClient.getCocktailClient().create(Api.class);
        }

    public static Api getFoodDetailsApi() {
        return ApiClient.getFoodDetailClient().create(Api.class);
    }

    public static Api getSearchResults() {
        return ApiClient.getFoodSearch().create(Api.class);
    }

    public static Api getMealPlannerApi() {
        return ApiClient.getMealPlanner().create(Api.class);
    }


    public static Api getWinesApi() {
        return ApiClient.getWineClient().create(Api.class);
    }

    public static Api getFoodApi() {
        return ApiClient.getSpoonClient().create(Api.class);
    }

    public static Api getBeerApi() {
        return ApiClient.getBeerClient().create(Api.class);
    }

    public static AlertDialog showDialogMessage(Context context, String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(title).setMessage(message).show();
        if (alertDialog.isShowing()) {
            alertDialog.cancel();
        }
        return alertDialog;
    }
}