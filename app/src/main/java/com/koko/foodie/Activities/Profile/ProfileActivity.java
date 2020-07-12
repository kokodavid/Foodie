package com.koko.foodie.Activities.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseUser;
import com.koko.foodie.Activities.AboutActivity;
import com.koko.foodie.Activities.home.HomeActivity;
import com.koko.foodie.R;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity implements com.koko.foodie.Activities.profile.UserProfileView {

    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.userImage)
    CircleImageView imageView;
    UserProfilePresenter presenter;
    private static final int RC_SIGN_IN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        presenter = new UserProfilePresenter(this);
        presenter.getUserinfo();

    }

    public void myRecipe(View view) {
        Intent profile = new Intent(ProfileActivity.this, UserProfileActivity.class);
        profile.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(profile);
    }

    @Override
    public void setUserinfo(FirebaseUser user) {
        if (user != null){
            Picasso.get().load(user.getPhotoUrl()).into(imageView);
            userName.setText(user.getDisplayName());
        }else
            google();

    }

    public void logout(View view) {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(task -> {
                    startActivity(new Intent(this,HomeActivity.class));
                });

    }

    private void google() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build()
        );

        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setLogo(R.drawable.foodie)      // Set logo drawable
                        .setTheme(R.style.AppTheme_NoActionBar)      // Set theme
                        .build(),
                RC_SIGN_IN);



    }

    public void about(View view) {
        Intent profile = new Intent(ProfileActivity.this, AboutActivity.class);
        profile.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(profile);
    }
}