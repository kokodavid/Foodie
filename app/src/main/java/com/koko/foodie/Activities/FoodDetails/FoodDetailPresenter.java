package com.koko.foodie.Activities.FoodDetails;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.koko.foodie.Models.FodieA;
import com.koko.foodie.Models.FoodInfo;
import com.koko.foodie.Models.SpoonMeals;
import com.koko.foodie.Models.TestModel;
import com.koko.foodie.Models.TestModelB;
import com.koko.foodie.Utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FoodDetailPresenter extends FoodDetailActivity {


    private FoodDetailView view;

    public FoodDetailPresenter(FoodDetailView view){
        this.view = view;
    }


    void getFoodDetails(int id){
        Call<TestModelB> foodInfoCall = Utils.getFoodDetailsApi().getFoodInformation(id, "6792adb5e9b544dc990c2499f73befb6");
        foodInfoCall.enqueue(new Callback<TestModelB>() {
            @Override
            public void onResponse(@NonNull Call<TestModelB> call,@NonNull Response<TestModelB> response) {
                if (response.isSuccessful() && response.body() != null){
                    view.setFoodInfo(response.body().getExtendedIngredients(),response.body().getAnalyzedInstructions());
                }else
                    view.onErrorLoading(response.message());
            }

            @Override
            public void onFailure(@NonNull Call<TestModelB> call, @NonNull Throwable t) {
                view.showLoading();
                t.getLocalizedMessage();
            }
        });
    }

}
