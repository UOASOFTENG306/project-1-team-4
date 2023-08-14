package com.example.softeng306_application.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.softeng306_application.R;
import com.example.softeng306_application.ViewModel.LoginViewModel;
import com.example.softeng306_application.ViewModel.RegisterViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    RegisterViewModel registerViewModel;
    TextInputEditText editTextEmail, editTextPassword, editTextUsername;
    Button registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextUsername = findViewById(R.id.username);
        registerButton = findViewById(R.id.btn_register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, username, password;
                email = String.valueOf(editTextEmail.getText());
                username = String.valueOf(editTextUsername.getText());
                password = String.valueOf(editTextPassword.getText());

                // Not adding in username for now
                registerViewModel.register(email, password);
            }
        });
    }
}