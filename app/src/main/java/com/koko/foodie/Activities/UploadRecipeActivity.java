package com.koko.foodie.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koko.foodie.Activities.home.HomeActivity;
import com.koko.foodie.Models.uploadData;
import com.koko.foodie.R;
import com.koko.foodie.Utils.Preferences;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.cancel)
    Button delete;
    private Validator validator;
    private DatabaseReference recipes;
    private KProgressHUD kProgressHUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_recipe);
        ButterKnife.bind(this);
        if (Preferences.getRName(this) != null && !Preferences.getRName(this).isEmpty()) {
            update();
        }
        // init validator
        validator = new Validator(this);
        validator.setValidationListener(this);


    }

    private void update() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String readyIn = intent.getStringExtra("readyIn");
        String serving = intent.getStringExtra("serving");
        String uploadedBy = intent.getStringExtra("uploadedBy");
        String ingredients = intent.getStringExtra("ingredients");
        String procedure = intent.getStringExtra("procedure");

        recipe_name.setText(name);
//            recipe_category.setText();
        recipe_count.setText(serving);
        recipe_ingredients.setText(ingredients);
        recipe_cookTime.setText(readyIn);
        recipe_procedure.setText(procedure);

        upload_recipe.setText("Update Recipe");

        // update recipe
        upload_recipe.setOnClickListener(v -> {
            Toast.makeText(this, "okay", Toast.LENGTH_SHORT).show();
        });

        // delete
        delete.setOnClickListener(v -> {
            kProgressHUD = new KProgressHUD(this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel("Please wait")
                    .setDetailsLabel("Deleting recipe")
                    .setCancellable(true)
                    .setAnimationSpeed(2)
                    .setDimAmount(0.5f)
                    .show();
            recipes = FirebaseDatabase.getInstance().getReference(RECIPES);
            Query query = recipes.orderByChild("name").equalTo(name);

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        ds.getRef().removeValue();
                        kProgressHUD.dismiss();
                        finish();
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e("delete", databaseError.toString());
                }
            });
        });
    }

    public void btnSelectImage(View view) {
        Intent photoPicker = new Intent(Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker, 1);

    }

    public void uploadImage() {
        kProgressHUD = new KProgressHUD(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setDetailsLabel("Adding recipe")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
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

       /* try {         progressDialog.show();
        } catch (WindowManager.BadTokenException e) {
            //use a log message
        }*/

        //get user_name information
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        String user_name = user.getDisplayName();
        String uid = FirebaseAuth.getInstance().getUid();
        //save uid locally
        Preferences.saveUid(uid, this);
        uploadData uploadData = new uploadData(
                imageUrl,
                user_name,
                recipe_name.getText().toString(),
                recipe_count.getText().toString(),
                recipe_category.getText().toString(),
                recipe_cookTime.getText().toString(),
                recipe_ingredients.getText().toString(),
                recipe_procedure.getText().toString(),
                uid

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
        String recipe_title = recipe_name.getText().toString();
//        String uid = FirebaseAuth.getInstance().getUid();
        recipes = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(RECIPES);
        recipes.child(recipe_title).setValue(uploadData);
        Toast.makeText(this, "Recipe successfully added", Toast.LENGTH_SHORT).show();
        kProgressHUD.dismiss();
        finish();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                // Successfully signed in

                /*Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                handleSignInResult(task);*/
                uploadImage();
            } else {

            }
        }
        if (resultCode == RESULT_OK) {
            uri = data.getData();
            recipeImage.setImageURI(uri);
        } else Toast.makeText(this, "Pick an Image", Toast.LENGTH_SHORT).show();


    }

    //google sign in result **NOT HANDLING**
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String name = account.getDisplayName();
            ;
        } catch (ApiException e) {
            e.printStackTrace();
        }

    }

    public void logout(View view) {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(task -> {
                    startActivity(new Intent(this, HomeActivity.class));
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

    public void cancelUpload(View view) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Preferences.saveRName(null, this);

    }
}