package com.example.softeng306_application.View;

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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.softeng306_application.R;
import com.example.softeng306_application.ViewModel.LoginViewModel;
import com.example.softeng306_application.ViewModel.RegisterViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;

public class RegisterActivity extends AppCompatActivity {

    private class ViewHolder{
        TextInputEditText editTextEmail, editTextPassword, editTextUsername;
        Button registerButton;
        ImageButton backButton;
        TextView mainLogoText;
    }
    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ViewHolder vh = new ViewHolder();

        vh.editTextEmail = findViewById(R.id.email);
        vh.editTextPassword = findViewById(R.id.password);
        vh.editTextUsername = findViewById(R.id.username);
        vh.registerButton = findViewById(R.id.btn_register);
        vh.backButton = findViewById(R.id.btn_back);
        vh.mainLogoText = findViewById(R.id.txt_logo);

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        setupUI(findViewById(R.id.linearlayout_register), vh);

        vh.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoginActivity(v);

            }
        });

        vh.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, username, password;
                email = String.valueOf(vh.editTextEmail.getText());
                username = String.valueOf(vh.editTextUsername.getText());
                password = String.valueOf(vh.editTextPassword.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(RegisterActivity.this, "Enter Email.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(username)){
                    Toast.makeText(RegisterActivity.this, "Enter Username.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterActivity.this, "Enter Password.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //OnClickListeners
                clickRegister(vh,v,email,password,username);


            }
        });
    }

    private void clickRegister(ViewHolder vh, View v, String email, String password, String username){
        registerViewModel.register(email, password, username).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    registerViewModel.addUserToDb(email,password,username);
                    Toast.makeText(RegisterActivity.this, "Account successfully created.", Toast.LENGTH_SHORT).show();
                    showMainActivity(v);
                } else {
                    // If sign in fails, display a message to the user
                    Toast.makeText(RegisterActivity.this, "Account failed to be created.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void showMainActivity(View v) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
    private void showLoginActivity(View v) {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }

    private void closeKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm.isAcceptingText()) {
            imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
    }

    private void setupUI(View v, ViewHolder vh) {
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