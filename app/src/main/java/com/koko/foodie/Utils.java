package com.koko.foodie;

import android.app.AlertDialog;
import android.content.Context;

import com.koko.foodie.Api.Api;
import com.koko.foodie.Api.ApiClient;


public class Utils {

    public static Api getApi() {
        return ApiClient.getFoodClient().create(Api.class);
    }

    public static AlertDialog showDialogMessage(Context context, String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(title).setMessage(message).show();
        if (alertDialog.isShowing()) {
            alertDialog.cancel();
        }
        return alertDialog;
    }
}