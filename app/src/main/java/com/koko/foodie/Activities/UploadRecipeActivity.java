package com.koko.foodie.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.koko.foodie.Activities.home.HomeActivity;
import com.koko.foodie.Models.uploadData;
import com.koko.foodie.R;

import java.net.URI;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UploadRecipeActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 007;

    @BindView(R.id.image_uploaded)
    ImageView  recipeImage;
    @BindView(R.id.txt_recipe_name)
    EditText recipe_name;
    @BindView(R.id.txt_recipe_category)
    EditText  recipe_category;
    @BindView(R.id.txt_recipe_country)
    EditText  recipe_country;
    @BindView(R.id.txt_recipe_ingredients)
    EditText  recipe_ingredients;
    public Uri uri;
    public String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_recipe);
        ButterKnife.bind(this);


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

    public void btnSelectImage(View view){
        Intent photoPicker = new Intent(Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){

            uri = data.getData();
            recipeImage.setImageURI(uri);
        }
        else Toast.makeText(this, "Pick an Image", Toast.LENGTH_SHORT).show();


        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }

    public void uploadImage(){
        StorageReference storageReference = FirebaseStorage.getInstance()
                .getReference().child("RecipeImage").child(uri.getLastPathSegment());

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageUrl = urlImage.toString();
                uploadRecipe();


            }
        });
    }

    public void btnUploadRecipe(View view) {
        uploadImage();
        uploadRecipe();

    }

    public void uploadRecipe(){

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Recipe Uploading......");

        try {
            progressDialog.show();
        }
        catch (WindowManager.BadTokenException e) {
            //use a log message
        }

        uploadData  uploadData = new uploadData(
          recipe_name.getText().toString(),
          recipe_category.getText().toString(),
          recipe_country.getText().toString(),
          recipe_ingredients.getText().toString(),
          imageUrl
        );

        String uid = FirebaseAuth.getInstance().getUid();



        FirebaseDatabase.getInstance().getReference("Recipe")
                .child(uid).setValue(uploadData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    progressDialog.dismiss();

                    Toast.makeText(UploadRecipeActivity.this, "Recipe Uploaded", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UploadRecipeActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

    }


    public void logout(View view) {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(UploadRecipeActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                });
    }
}