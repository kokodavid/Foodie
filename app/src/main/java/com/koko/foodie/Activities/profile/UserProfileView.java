package com.koko.foodie.Activities.Profile;

import com.google.firebase.auth.FirebaseUser;

public interface UserProfileView {

    void setUserinfo(FirebaseUser user);
    void showLoading();
    void hideloading();
    void onErrorLoading(String message);

//    void setUserRecipes(ArrayList<DataSnapshot> userRecipes);
}
