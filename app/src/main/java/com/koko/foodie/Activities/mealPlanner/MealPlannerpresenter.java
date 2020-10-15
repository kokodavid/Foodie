package com.koko.foodie.Activities.mealPlanner;

import androidx.annotation.NonNull;

import com.koko.foodie.Models.MealPlan;
import com.koko.foodie.Utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealPlannerpresenter extends MealPlanner{

    private MealView view;
    public MealPlannerpresenter(MealView view){
        this.view = view;
    }

    void getMealPlanner(Integer Calories,String diet,String exclude){
        view.showLoading();
        Call<MealPlan> mealPlanCall = Utils
                .getMealPlannerApi()
                .getMealPlan("day",Calories,diet,exclude,"6792adb5e9b544dc990c2499f73befb6");
        mealPlanCall.enqueue(new Callback<MealPlan>() {
            @Override
            public void onResponse(@NonNull Call<MealPlan> call,@NonNull Response<MealPlan> response) {
                view.hideloading();
                if (response.isSuccessful() && response.body() != null){
                    view.setMealPlan(response.body().getMeals(),response.body().getNutrients());
                }else
                    view.onErrorLoading(response.message());
            }

            @Override
            public void onFailure(@NonNull Call<MealPlan> call,@NonNull Throwable t) {
                view.showLoading();
                t.getLocalizedMessage();
            }
        });
    }
}
