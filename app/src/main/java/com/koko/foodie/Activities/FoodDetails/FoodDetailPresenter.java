package com.koko.foodie.Activities.FoodDetails;

import androidx.annotation.NonNull;

import com.koko.foodie.Models.FoodInfo;
import com.koko.foodie.Utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodDetailPresenter {

    private FoodDetailView view;

    public FoodDetailPresenter(FoodDetailView view){
        this.view = view;
    }


    void getFoodDetails(){
        Call<FoodInfo> foodInfoCall = Utils.getFoodDetailsApi().getFoodInformation("1","information?","6792adb5e9b544dc990c2499f73befb6");
        foodInfoCall.enqueue(new Callback<FoodInfo>() {
            @Override
            public void onResponse(@NonNull Call<FoodInfo> call,@NonNull Response<FoodInfo> response) {
                if (response.isSuccessful() && response.body() != null){
                    view.setFoodInfo(response.body().getName());
                }
            }

            @Override
            public void onFailure(@NonNull Call<FoodInfo> call,@NonNull Throwable t) {

            }
        });
    }

}
