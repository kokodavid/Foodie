package com.koko.foodie.Activities.profile;

import com.google.firebase.auth.FirebaseUser;

public interface UserProfileView {

    void setUserinfo(FirebaseUser user);
//    void setUserRecipes(ArrayList<DataSnapshot> userRecipes);
}
