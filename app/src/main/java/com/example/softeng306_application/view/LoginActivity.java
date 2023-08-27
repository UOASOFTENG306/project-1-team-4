package com.example.softeng306_application.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.softeng306_application.R;
import com.example.softeng306_application.viewmodel.LoginViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;

public class LoginActivity extends AppCompatActivity implements Activity  {
    private LoginViewModel loginViewModel;

    private class ViewHolder{
        TextInputEditText editTextEmail, editTextPassword;
        Button loginButton, createNewAccountButton;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        RestaurantFirestoreDataProvider restaurantFirestoreDataProvider = new RestaurantFirestoreDataProvider();
//        restaurantFirestoreDataProvider.addRestaurantToFirestore();
//        UserFirestoreDataProvider userFirestoreDataProvider = new UserFirestoreDataProvider();
//         userFirestoreDataProvider.addFavouritesToDB();

        setContentView(R.layout.activity_login);
        ViewHolder vh = new ViewHolder();
        vh.editTextEmail = findViewById(R.id.email);
        vh.editTextPassword = findViewById(R.id.password);
        vh.loginButton = findViewById(R.id.btn_login);
        vh.createNewAccountButton = findViewById(R.id.btn_create_new_account);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        setupUI(findViewById(R.id.linearlayout_login));

        //OnClickListeners
        clickLogin(vh);
        clickCreateNewButton(vh);

    }

    private void clickCreateNewButton(ViewHolder vh){
        vh.createNewAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterActivity(v);
            }
        });
    }

    private void clickLogin(ViewHolder vh){

        vh.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                email = String.valueOf(vh.editTextEmail.getText());
                password = String.valueOf(vh.editTextPassword.getText());
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(LoginActivity.this, "Enter Email.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "Enter Password.", Toast.LENGTH_SHORT).show();
                    return;
                }
                loginViewModel.signIn(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login successful.",
                                    Toast.LENGTH_SHORT).show();
                            showMainActivity(v);
                        } else {
                            // If sign in fails, display a message to the user
                            Toast.makeText(LoginActivity.this, "Login failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    //TODO: ADD THIS FUNCTIONALITY TO AN ABSTRACT CLASS CALLED ACTIVITY LATER
    private void showRegisterActivity(View v) {
        Intent registerIntent = new Intent(this, RegisterActivity.class);
        startActivity(registerIntent);
    }

    private void showMainActivity(View v) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }

    private void closeKeyboard() {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if(imm.isAcceptingText()) {
                imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
            }
    }

    private void setupUI(View v) {
        // Setup touch listener for non-text box views to hide keyboard
        if (!(v instanceof TextInputEditText)) {
            v.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    closeKeyboard();
                    return false;
                }
            });
        }
    }


}