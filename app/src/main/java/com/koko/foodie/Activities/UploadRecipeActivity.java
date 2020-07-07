package com.koko.foodie.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.koko.foodie.Activities.home.HomeActivity;
import com.koko.foodie.Models.uploadData;
import com.koko.foodie.R;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT;

public class UploadRecipeActivity extends AppCompatActivity implements Validator.ValidationListener {

    public static final String RECIPES = "Recipes";
    private static final int RC_SIGN_IN = 0;
    public Uri uri;
    public String imageUrl;
    @BindView(R.id.image_uploaded)
    ImageView recipeImage;
    @NotEmpty
    @BindView(R.id.txt_recipe_name)
    EditText recipe_name;
    @NotEmpty
    @BindView(R.id.txt_recipe_category)
    EditText recipe_category;
    @NotEmpty
    @BindView(R.id.txt_recipe_serving_count)
    EditText recipe_count;
    @NotEmpty
    @BindView(R.id.txt_recipe_ingredients)
    EditText recipe_ingredients;
    @NotEmpty
    @BindView(R.id.txt_recipe_cooktime)
    EditText recipe_cookTime;
    @NotEmpty
    @BindView(R.id.txt_recipe_procedure)
    EditText recipe_procedure;
    @BindView(R.id.upload_recipe)
    Button upload_recipe;
    String user_name;
    private Validator validator;
    private DatabaseReference recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_recipe);
        ButterKnife.bind(this);


        // init validator
        validator = new Validator(this);
        validator.setValidationListener(this);

    }

    public void btnSelectImage(View view) {
        Intent photoPicker = new Intent(Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker, 1);

    }

    public void uploadImage() {
        StorageReference storageReference = FirebaseStorage.getInstance()
                .getReference().child("RecipeImage").child(uri.getLastPathSegment());

        storageReference.putFile(uri).addOnSuccessListener(taskSnapshot -> {

            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
            while (!uriTask.isComplete()) ;
            Uri urlImage = uriTask.getResult();
            imageUrl = urlImage.toString();
            uploadRecipe();


        });
    }

    public void btnUploadRecipe(View view) {
        validator.validate();
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

    public void uploadRecipe() {
//        ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Recipe Uploading......");

       /* try {         progressDialog.show();
        } catch (WindowManager.BadTokenException e) {
            //use a log message
        }*/

        uploadData uploadData = new uploadData(
                imageUrl,
                "name",
                recipe_name.getText().toString(),
                recipe_count.getText().toString(),
                recipe_category.getText().toString(),
                recipe_cookTime.getText().toString(),
                recipe_ingredients.getText().toString(),
                recipe_procedure.getText().toString()

        );

//        String uid = FirebaseAuth.getInstance().getUid();
//
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//
//
//
//
//        FirebaseDatabase.getInstance().getReference("Recipe")
//                .child(uid).setValue(uploadData).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//
//                if (task.isSuccessful()){
////                    progressDialog.dismiss();
//
//                    Toast.makeText(UploadRecipeActivity.this, "Recipe Uploaded", Toast.LENGTH_SHORT).show();
//
//                    finish();
//                }
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(UploadRecipeActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
////                progressDialog.dismiss();
//            }
//        });
//

        String uid = FirebaseAuth.getInstance().getUid();
        recipes = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(RECIPES);
        recipes.child(uid).setValue(uploadData);
        Toast.makeText(this, "Recipe successfully added", Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                handleSignInResult(task);
                uploadImage();
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
        if (resultCode == RESULT_OK) {
            uri = data.getData();
            recipeImage.setImageURI(uri);
        } else Toast.makeText(this, "Pick an Image", Toast.LENGTH_SHORT).show();


    }

    //google sign in result
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String name = account.getDisplayName();
            Uri image_url = account.getPhotoUrl();
        } catch (ApiException e) {
            e.printStackTrace();
        }

    }

    public void logout(View view) {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(task -> {
                    Intent intent = new Intent(UploadRecipeActivity.this, HomeActivity.class);
                    startActivity(intent);
                });
    }

    @Override
    public void onValidationSucceeded() {
        google();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            // Display error messages
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}