package com.example.softeng306_application.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.softeng306_application.R;
import com.example.softeng306_application.ViewModel.LoginViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;

public class LoginActivity extends AppCompatActivity implements Activity  {
    private LoginViewModel loginViewModel;

    private class ViewHolder{
        TextInputEditText editTextEmail, editTextPassword;
        Button loginButton, createNewAccountButtton;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewHolder vh = new ViewHolder();
        vh.editTextEmail = findViewById(R.id.email);
        vh.editTextPassword = findViewById(R.id.password);
        vh.loginButton = findViewById(R.id.btn_login);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        vh.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                email = String.valueOf(vh.editTextEmail.getText());
                password = String.valueOf(vh.editTextPassword.getText());
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
        vh.createNewAccountButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterActivity(v);
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


}