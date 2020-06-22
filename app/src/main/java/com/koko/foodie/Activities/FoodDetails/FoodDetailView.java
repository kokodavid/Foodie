package com.koko.foodie.Activities.FoodDetails;


import com.koko.foodie.Models.FodieA;
import com.koko.foodie.Models.Food;
import com.koko.foodie.Models.FoodInfo;
import com.koko.foodie.Models.SpoonMeals;
import com.koko.foodie.Models.TestModel;
import com.koko.foodie.Models.TestModelB;

import java.util.List;

public interface FoodDetailView {
    void showLoading();
    void hideloading();
    void onErrorLoading(String message);
    void setFoodInfo(List<TestModelB.ExtendedIngredient> foodName,List<TestModelB.AnalyzedInstruction> instructions);
    void setFoodInstructions(List<TestModelB.AnalyzedInstruction> foodInstructions);


}
