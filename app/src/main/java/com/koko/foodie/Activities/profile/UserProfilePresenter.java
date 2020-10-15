package com.koko.foodie.Activities.Profile;

import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.koko.foodie.Activities.Profile.UserProfileActivity;
import com.koko.foodie.R;

public class UserProfilePresenter extends UserProfileActivity {

    private UserProfileView view;

    public UserProfilePresenter(UserProfileView view) {
        this.view = view;
    }

    void getUserinfo() {
        view.hideloading();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        view.setUserinfo(user);

    }

//    void getUserRecipes(){
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String uid = user.getUid();
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Recipes");
//
//        reference.orderByChild("uid").equalTo(uid).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()){
//                    ArrayList<DataSnapshot> user_recipe = new ArrayList<DataSnapshot>();
//                    for (DataSnapshot ds: dataSnapshot.getChildren()){
//
//                        user_recipe.add(ds);
//                    }
//                    view.setUserRecipes(user_recipe);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }

}
