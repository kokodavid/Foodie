package com.koko.foodie.Activities.WinePairing;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.koko.foodie.Activities.home.HomeView;
import com.koko.foodie.Models.Wine;
import com.koko.foodie.Utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class WinePresenter extends WinePairingActivity {

    private WineView view;
    public WinePresenter(WineView view) {
        this.view = view;
    }


    public List<Wine> wines;

    void getWines(String value){
        view.showLoading();
            Call<Wine> winesCall = Utils.getWinesApi().getWines(
                    value,
                    "6792adb5e9b544dc990c2499f73befb6");
            winesCall.enqueue(new Callback<Wine>() {
                @Override
                public void onResponse(@NonNull Call<Wine> call,@NonNull Response<Wine> response) {
                    view.hideloading();
                    if (response.isSuccessful() && response.body() != null){
                        view.setWine(response.body().getProductMatches(),response.body().getPairingText());
                        Log.d(TAG, "onResponse: nikubaya");
                    }else
                        view.onErrorLoading(response.message());
                }

                @Override
                public void onFailure(@NonNull Call<Wine> call,@NonNull Throwable t) {
                    view.showLoading();
                    t.getLocalizedMessage();
                }
            });


        }



}
