package com.koko.foodie.Activities.Profile;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.koko.foodie.Activities.AboutActivity;
import com.koko.foodie.Activities.Favorite.FavoritesActivity;
import com.koko.foodie.Activities.home.HomeActivity;
import com.koko.foodie.BuildConfig;
import com.koko.foodie.R;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity implements UserProfileView {

    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.Rate)
    TextView Rate;
    @BindView(R.id.userImage)
    CircleImageView imageView;
    UserProfilePresenter presenter;
    @BindView(R.id.bottomnav)
    BottomNavigationView bm;
    private static final int RC_SIGN_IN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        presenter = new UserProfilePresenter(this);
        presenter.getUserinfo();

        bm=(BottomNavigationView)findViewById(R.id.bottomnav);
        bm.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case R.id.favorites:
                        Intent user_profile = new Intent(ProfileActivity.this, FavoritesActivity.class);
                        user_profile.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(user_profile);
                        break;

                }
                return true;
            }
        });

    }

    public void myRecipe(View view) {
        Intent profile = new Intent(ProfileActivity.this, UserProfileActivity.class);
        profile.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(profile);
    }

    @Override
    public void setUserinfo(FirebaseUser user) {
        if (user != null) {
            Picasso.get().load(user.getPhotoUrl()).into(imageView);
            userName.setText(user.getDisplayName());
        } else
            google();

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideloading() {

    }

    @Override
    public void onErrorLoading(String message) {

    }

    public void logout(View view) {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(task -> {
                    startActivity(new Intent(this, HomeActivity.class));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Picasso.get().load(user.getPhotoUrl()).into(imageView);
                userName.setText(user.getDisplayName());
                /*Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                handleSignInResult(task);*/
            } else {

            }
        }
      


    }

    public void about(View view) {
        Intent profile = new Intent(ProfileActivity.this, AboutActivity.class);
        profile.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(profile);
    }

    public void share(View view) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Foodie Recipe App");
            String shareMessage = "\nLet me recommend you this application\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch (Exception e) {
            //e.toString();
        }
    }

    public void rate(View view) {
        launchMarket();
    }


        private void launchMarket() {
            Uri uri = Uri.parse("market://details?id=" + getPackageName());
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            try {
                startActivity(goToMarket);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, R.string.couldnt_launch_market, Toast.LENGTH_LONG).show();
            }
        }
    }


