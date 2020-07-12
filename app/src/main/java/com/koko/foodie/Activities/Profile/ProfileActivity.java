package com.koko.foodie.Activities.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseUser;
import com.koko.foodie.Activities.home.HomeActivity;
import com.koko.foodie.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity implements com.koko.foodie.Activities.profile.UserProfileView {

    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.userImage)
    CircleImageView imageView;
    UserProfilePresenter presenter;

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
        Picasso.get().load(user.getPhotoUrl()).into(imageView);
        userName.setText(user.getDisplayName());
    }
}